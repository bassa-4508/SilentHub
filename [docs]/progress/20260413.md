# SilentHub 実装進捗（Claude引き継ぎメモ）

最終更新: 2026-04-13

このファイルは、次セッションの Claude が即座に文脈を掴めるようにするための引き継ぎメモ。
コードから自明なことは書かない。**決定の理由** と **次にやること** を残す。

---

## 完了ステップ

### Step 1 — プロジェクト足場 + UI 骨組み
- Kotlin 2.2.10 / Compose BOM 2026.02.01 / Material3 / Navigation Compose 2.8.4
- Bottom Nav 3タブ（Time / Contacts / Settings、中身は見出しテキストのみ）
- Theme は design.md 準拠に置き換え。**dynamic color は廃止**（design.md「アクセントは `primary` のみ」の方針）
- Package: `com.akaokatubasa.silenthub`（SilentHub.md の `com.yourname.silenthub` は仮名なので実パッケージに置換済み）
- 画面のテキスト色は `MaterialTheme.colorScheme.onBackground` 参照のみ（直接 `Color()` 禁止 — claude.md §5）

### Step 2 — Data 層
- Models（`data/model/`）: `Contact` / `NotificationItem` / `DataContainer` 全て `@Serializable`
- `JsonDataSource`: `context.filesDir/data.json` 読み書き。**初回不在なら空コンテナを自動生成して返す**。load/save とも `try { ... } catch (Throwable)` でフォールバック（claude.md §9 クラッシュ禁止）
- `SilentRepository`: ロジック無しのラップ。`copy(contacts = current.contacts + new)` で追記のみ
- kotlinx-serialization 1.6.3。**コンパイラプラグインは `version.ref = "kotlin"` にする**（ライブラリ 1.6.3 とは別バージョン扱い）
- `encodeDefaults = true` / `ignoreUnknownKeys = true`（前方互換のため）

### Step 3 — UseCase 層
- `domain/usecase/` 配下に 1 ファイル 1 UseCase
- `GetContactsUseCase` / `AddContactUseCase` / `GetNotificationsUseCase` / `AddNotificationUseCase`
- 全て `suspend operator fun invoke(...) = withContext(Dispatchers.IO) { repository.xxx() }` の形
- Coroutines は `lifecycle-runtime-ktx` 経由の**推移依存**なので明示宣言していない（claude.md §12「不要なライブラリ追加禁止」）

---

## 次にやること（Step 4: ViewModel + 手動 DI）

1. `TimeViewModel` / `ContactsViewModel` を作成
2. UseCase をコンストラクタ注入
3. `Application` サブクラス or `MainActivity` で `SilentRepository(JsonDataSource(applicationContext))` を 1 回だけ生成
4. `ViewModelProvider.Factory` 経由で ViewModel に UseCase を渡す
5. 状態は `StateFlow<UiState>` で公開、UI は `collectAsState()`
6. まだ実 UI は書かない（claude.md §19「1回で全部作らない」）。テキスト＋リストで動作確認できれば OK

**禁止事項**: Hilt / Koin / Dagger は導入しない（claude.md §12）。**手動 DI のみ**。

---

## Step 5 以降の見通し（仮）

1. Time 画面の実 UI + AlarmManager（exact alarm）スケジューリング
2. 通知 `BroadcastReceiver` + フルスクリーン `Activity`（`FLAG_SHOW_WHEN_LOCKED` / `FLAG_TURN_SCREEN_ON`）
3. Contacts 画面 CRUD + URI 起動（LINE / X / Instagram / Email / Phone）
4. Settings 画面（JSON import / export）

---

## 重要な決定事項（将来の Claude が迷わないように）

- **色の直接指定は全面禁止**: 画面内では `MaterialTheme.colorScheme.*` のみ参照。`Color.kt` がトークンの置き場
- **I/O は UseCase で `Dispatchers.IO` に載せる**: DataSource / Repository はブロッキング API のまま据え置き。ViewModel から直接呼ぶと ANR リスク
- **ルート定義は `sealed class Destination + data object Time/Contacts/Settings`**: Nav Compose 2.8 で推奨の形
- **Bottom Nav アイコンは core セットのみ**: `material-icons-extended` は重いので追加していない。`DateRange / Person / Settings` は core に含まれる
- **`.claude/` は `.gitignore` 済み**: セッション固有のローカル設定なのでコミットしない
- **`[docs]` フォルダ名の `[]`**: プロジェクトルート直下なので Gradle ビルドパスには影響しない
- **ブランチ**: `main` は安定版、`dev` が作業ブランチ（SilentHub.md §12 準拠）

---

## 既知の未検証事項

- **Gradle Sync の成否**: Compose BOM 2026.02.01 × Navigation 2.8.4 × Kotlin Serialization plugin の組合せで Sync が通るかは**未確認**。Sync で落ちたら Navigation を 2.8.x の別マイナーにずらすのが第一選択
- **起動テスト未実施**: 実機/エミュレータで一度も走らせていない。Step 4 完了時点で確認する予定
- **`data.json` の生成テスト**: UseCase 経由で呼んでいないため、まだ実ファイルは生成されていない

---

## 実装順序のメタルール（claude.md §19）

1 セッションで全部作らない。必ず **Step 単位で止めて** 確認してから次へ進む。
次セッションも同じリズムで Step 4 → Step 5 → ... と段階的に進めること。

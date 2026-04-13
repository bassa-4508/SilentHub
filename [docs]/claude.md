# Claude Implementation Guide (claude.md)

---

## 0. Purpose

このドキュメントは、AI（Claude等）がSilentHubを実装する際に
**一貫性・品質・拡張性を維持するためのルールセット**である。

design.mdはUI定義、本ファイルは**実装ルール**を定義する。

---

## 1. 基本原則

* シンプルに保つ（過剰実装禁止）
* 1機能ずつ確実に完成させる
* 既存構造を壊さない
* UIより機能の信頼性を優先

---

## 2. 技術スタック（厳守）

* Kotlin
* Jetpack Compose
* Material 3
* MVVM + Clean Architecture
* Coroutines

---

## 3. アーキテクチャルール

### レイヤー構造

```
UI (Compose)
 ↓
ViewModel
 ↓
UseCase
 ↓
Repository
 ↓
DataSource
```

---

### ルール

* UIは状態表示のみ
* ビジネスロジックはUseCaseに置く
* データアクセスはRepository経由のみ
* DataSourceは実装詳細

---

## 4. コーディング規約

### 命名

* クラス: PascalCase
* 関数: camelCase
* 定数: UPPER_SNAKE_CASE

---

### ファイル分割

* 1ファイル1責務
* 500行超え禁止
* UIとロジックは分離

---

### コメント

* 必要最小限
* 「なぜ」を書く（whatはコードで分かる）

---

## 5. UI実装ルール

* Material3コンポーネント使用
* Theme.ktで色管理
* 直接Color指定禁止

---

### Compose原則

* @Composableは副作用を持たない
* 状態はViewModelで管理
* rememberは最小限

---

## 6. 状態管理

* StateFlow / MutableStateFlow使用
* UIはcollectAsState()

---

### ViewModel例

```kotlin id="7i6a8s"
class TimeViewModel : ViewModel() {
    private val _state = MutableStateFlow(TimeState())
    val state = _state

    fun addNotification() {
        // logic
    }
}
```

---

## 7. 通知実装ルール（重要）

* AlarmManagerを使用
* exact alarmを優先
* BroadcastReceiverで受信

---

### フルスクリーン通知

必須条件：

* 画面ON
* ロック画面表示
* 無音

---

### 禁止

* Handler遅延処理
* 非公式API使用

---

## 8. データ管理

* JSONファイル保存
* Repository経由のみアクセス

---

### 禁止

* UIから直接ファイル操作
* グローバル変数

---

## 9. エラーハンドリング

* try-catchを適切に使用
* クラッシュ禁止
* fallback処理を書く

---

## 10. パフォーマンス

* 無駄な再Compose禁止
* LazyColumn使用
* 重い処理はDispatchers.IO

---

## 11. 拡張ルール

* 既存コードを壊さない
* interfaceを使って拡張可能にする
* 将来的なRoom移行を考慮

---

## 12. 禁止事項（重要）

* 巨大クラス作成
* UIにロジックを書く
* ハードコード値乱用
* 不要なライブラリ追加

---

## 13. 実装フロー（必須）

AIは以下の順番で実装すること：

1. データモデル
2. Repository
3. UseCase
4. ViewModel
5. UI
6. 通知機能

---

## 14. 出力ルール（AI向け）

* ファイルごとに分割して出力
* 完全なコードを出す（省略禁止）
* importを省略しない
* 動作可能な状態で出力

---

## 15. テスト方針

* クラッシュしないこと
* 通知が確実に動くこと
* データが消えないこと

---

## 16. Gitルール

* 小さくコミット
* 意味のある単位で分割

---

## 17. 優先順位

1. 通知の確実性
2. データの安全性
3. UIの一貫性

---

## 18. 最終ゴール

* 静音環境で確実に時間を伝える
* 連絡先を一元管理できる
* シンプルで壊れない

---

## 19. AIへの最重要指示

* 迷ったらシンプルな実装を選択
* 1回で全部作ろうとしない
* 必ず段階的に実装する

---

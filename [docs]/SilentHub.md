# SilentHub - Androidアプリ開発仕様書（AI実装完全版）

---

## 0. プロジェクト情報

### アプリ名

SilentHub

### パッケージ名

com.yourname.silenthub

### 概要

静音環境での時間通知と、分散した連絡先の統合管理を行うAndroidアプリ。
完全オフライン動作を前提とし、JSONベースでデータを管理する。

---

## 1. コアコンセプト

* 音を出さずに確実に通知する
* 人単位で情報を統合する
* 完全ローカルで完結
* 拡張しても破綻しない構造

---

## 2. 機能仕様

### 2.1 サイレント通知

#### 要件

* 指定時刻で通知
* 音・バイブなし
* 画面ON + フルスクリーン表示

#### 実装

* AlarmManager使用
* BroadcastReceiverで受信
* Activityでフルスクリーン表示

#### 必須フラグ

* FLAG_SHOW_WHEN_LOCKED
* FLAG_TURN_SCREEN_ON

---

### 2.2 連絡先統合

#### 要件

* 人単位で管理
* 複数の連絡先を紐付け

#### 対応

* LINE
* X
* Instagram
* Email
* Phone

#### 機能

* CRUD
* タグ管理
* URI起動

---

### 2.3 データ管理

* JSON保存
* 完全オフライン
* エクスポート / インポート対応

---

## 3. データ構造

### data.json

```json
{
  "contacts": [],
  "notifications": []
}
```

---

## 4. 技術スタック

* Kotlin
* Jetpack Compose
* MVVM + Clean Architecture
* Coroutines
* AlarmManager

---

## 5. UI設計

### デザイン

Material Design準拠

### ナビゲーション

* Bottom Navigation

### タブ

* Time
* Contacts
* Settings

---

## 6. ディレクトリ構成

```
silenthub/
 ├── app/
 │    ├── src/main/java/com/yourname/silenthub/
 │    │    ├── MainActivity.kt
 │    │
 │    │    ├── ui/
 │    │    │    ├── navigation/NavGraph.kt
 │    │    │    ├── screens/
 │    │    │    │    ├── time/
 │    │    │    │    ├── contacts/
 │    │    │    │    └── settings/
 │    │
 │    │    ├── domain/
 │    │    │    ├── model/
 │    │    │    └── usecase/
 │    │
 │    │    ├── data/
 │    │    │    ├── repository/
 │    │    │    └── datasource/
 │    │
 │    │    ├── notification/
 │    │    └── util/
 │
 ├── .env
 ├── .env.dev
 ├── .env.prod
 ├── .gitignore
 ├── README.md
 └── build.gradle.kts
```

---

## 7. 環境変数管理

### .env（例）

```
APP_NAME=SilentHub
DEBUG_MODE=true
```

---

### Gradle読み込み

```kotlin
val env = java.util.Properties().apply {
    load(rootProject.file(".env").inputStream())
}

val appName = env["APP_NAME"] as String

buildConfigField("String", "APP_NAME", "\"$appName\"")
```

---

## 8. .gitignore

```
.env
.env.*
local.properties
*.keystore
/build
```

---

## 9. 外部連携

### URIスキーム

* LINE → line://ti/p/{id}
* X → https://x.com/{id}
* Instagram → https://instagram.com/{id}

---

## 10. 実装ルール

* UIは状態表示のみ
* ロジックはViewModelへ
* Repository経由でデータアクセス
* Context直接使用禁止

---

## 11. 実装優先順位

1. 通知機能
2. JSON保存
3. UI基盤
4. 連絡先
5. 拡張機能

---

## 12. GitHub運用

### リポジトリ名

silenthub

### ブランチ戦略

* main
* dev

---

### コミット規約

* feat: 機能追加
* fix: バグ修正
* refactor: 改善

---

## 13. README.md

### 構成

* タイトル
* 概要
* 機能
* スクリーンショット
* セットアップ
* 今後の予定

---

## 14. READMEテンプレ

```md
# SilentHub

## Overview
Silent notification & contact hub app for Android.

## Features
- Silent time alerts
- Contact aggregation
- Fully offline

## Setup
1. Clone repo
2. Add .env
3. Build project

## Roadmap
- Timer feature
- Widget
- Cloud sync (optional)
```

---

## 15. 拡張設計

* JSON → Room移行可能
* SNS API対応可能
* モジュール分離済み

---

## 16. 完了条件

* 無音通知が確実に動作
* JSON保存が機能
* UIが崩れない
* クラッシュしない

---

## 17. AIへの指示

* Kotlin + Composeで実装
* Clean Architectureを厳守
* 各ファイルを分割して生成
* 1機能ずつ実装する

---

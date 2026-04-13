# SilentHub Design System (Material 3 Adaptation)

---

## 1. Design Philosophy

本デザインはApple的な「静寂・余白・プロダクト中心」の思想をベースにしつつ、
Material Design（Material 3）の実装思想に最適化する。

### コア原則

* UIは主役ではない（情報・機能が主役）
* 色は最小限（ほぼモノトーン + 1アクセント）
* 静的で落ち着いたUI（過剰なアニメーション禁止）
* 視覚通知時のみ強いコントラストを使う

---

## 2. Color System（Material 3対応）

### Core Colors

```yaml
primary: #0071e3        # インタラクション専用（Apple Blueベース）
onPrimary: #ffffff

background: #f5f5f7
onBackground: #1d1d1f

surface: #ffffff
onSurface: #1d1d1f

surfaceVariant: #e5e5ea
onSurfaceVariant: rgba(0,0,0,0.6)
```

---

### Dark Theme

```yaml
background: #000000
onBackground: #ffffff

surface: #1c1c1e
onSurface: #ffffff

primary: #2997ff
```

---

### ルール

* アクセントカラーは `primary` のみ
* 複数色使用は禁止
* 通知時のみ強調色を強く使う

---

## 3. Typography（Material寄せ）

※SF ProはAndroid標準では使えないため、Roboto / Google Sansで再現

### フォント

* Headline → Google Sans / Roboto Medium
* Body → Roboto Regular

---

### スケール

| Role            | Size | Weight | LineHeight | 特徴      |
| --------------- | ---- | ------ | ---------- | ------- |
| Headline Large  | 48sp | 600    | 1.1        | 強いインパクト |
| Headline Medium | 32sp | 600    | 1.15       | セクション   |
| Title           | 22sp | 500    | 1.2        | カード     |
| Body            | 16sp | 400    | 1.5        | 通常      |
| Label           | 14sp | 500    | 1.3        | UI要素    |

---

### ルール

* 行間はやや詰め気味（Apple寄せ）
* letterSpacingは軽くマイナス寄せ（Composeで微調整）
* 強調はweightで表現（色で表現しない）

---

## 4. Component Design（Compose前提）

---

### Buttons

#### Primary Button

* containerColor: primary
* contentColor: white
* shape: RoundedCornerShape(8dp)

#### Secondary（Text Button）

* 色のみ primary
* 背景なし

---

### FAB

* 右下固定
* shape: Circle
* 色: primary

---

### Cards

* shape: RoundedCornerShape(12dp)
* elevation: 1dp〜3dp（最小）
* border: なし

👉 Appleの「影ほぼなし」をMaterialで再現

---

### Dialog

* 中央表示
* シンプル
* 余白多め（24dp以上）

---

## 5. Layout System

---

### 基本ルール

* 8dpグリッド
* 横余白：16dp
* セクション間：32〜64dp

---

### 構成

* Full widthベース
* 中央寄せコンテンツ

---

### リズム

* 「余白」で区切る
* 色切り替えは最小限（Appleほど強くしない）

---

## 6. Screen Design Rules

---

### Time Screen

* 最重要画面
* 情報は最小限
* カード形式

---

### Contact Screen

* リスト主体
* タグで整理
* アイコン最小限

---

### Notification Screen（重要）

* フルスクリーン
* 背景：黒 or 白
* テキスト巨大表示

---

## 7. Motion

---

### 基本方針

* 最小限
* 速い（200ms以下）

---

### 使用するもの

* fadeIn / fadeOut
* scale（軽く）

---

### 禁止

* バウンス
* 過剰なトランジション

---

## 8. SilentHub特有ルール

---

### 通常状態

* 落ち着いたUI
* 低コントラスト

---

### 通知時

* 強いコントラスト
* 一瞬の視覚刺激
* フルスクリーン

---

## 9. Accessibility

* 最小タップ領域：48dp
* コントラスト確保
* フォーカスリングあり（primary）

---

## 10. Compose Implementation Rule

AIは以下を守ること：

* Material3コンポーネント使用
* ColorSchemeで管理
* Theme.ktで一元管理
* Hardcode禁止

---

## 11. Theme.kt例

```kotlin
private val LightColors = lightColorScheme(
    primary = Color(0xFF0071E3),
    onPrimary = Color.White,
    background = Color(0xFFF5F5F7),
    onBackground = Color(0xFF1D1D1F),
    surface = Color.White,
    onSurface = Color(0xFF1D1D1F)
)
```

---

## 12. Do / Don't

### Do

* シンプル
* 情報最優先
* 色は最小限

### Don't

* カラフルにする
* アニメーション多用
* 独自UIを増やす

---

## 13. Design Summary

SilentHubのUIは以下を満たす：

* 静かである
* 主張しない
* 必要なときだけ強く出る

---

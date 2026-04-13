

<picture>
  <source
    width="100%"
    srcset="./docs/content/public/banner-dark-1280x640.png"
    media="(prefers-color-scheme: dark)"
  />
  <source
    width="100%"
    srcset="./docs/content/public/banner-light-1280x640.png"
    media="(prefers-color-scheme: light), (prefers-color-scheme: no-preference)"
  />
  <img width="100%" src="./docs/content/public/banner-light-1280x640.png" />
</picture>

<h1 align="center">SilentHub</h1>

<p align="center">
A minimal, silent utility app designed for focus environments.
<br/>
No sound. No vibration. Just information — exactly when you need it.
</p>

<p align="center">
  <a href="./docs/README.ja.md">日本語</a> •
  <a href="./docs/README.en.md">English</a>
</p>

<p align="center">
  <img src="https://img.shields.io/github/license/YOUR_NAME/SilentHub?style=flat">
  <img src="https://img.shields.io/github/stars/YOUR_NAME/SilentHub?style=flat">
  <img src="https://img.shields.io/github/issues/YOUR_NAME/SilentHub?style=flat">
</p>

---

## ✨ What is SilentHub?

SilentHub is a **fully offline Android utility app** focused on:

- ⏰ Silent time notifications (no sound / no vibration)
- 👤 Unified contact management (SNS / Email / Phone)
- 📦 JSON-based data portability

Built for:
- Libraries
- Study rooms
- Quiet workplaces

---

## 🚀 Features

### 1. Silent Time Notification
- Screen wake-up instead of sound
- Fullscreen time display
- Works even on lock screen
- Uses AlarmManager (exact timing)

### 2. Contact Hub
- Merge multiple identities into one person
- Supported:
  - LINE
  - X (Twitter)
  - Instagram
  - Email
  - Phone

### 3. JSON Data System
- Fully offline
- Import / Export
- Cross-device sharing via copy-paste

---

## 📱 Screenshots

<p align="center">
  <img src="./docs/content/screenshots/screen1.png" width="30%">
  <img src="./docs/content/screenshots/screen2.png" width="30%">
  <img src="./docs/content/screenshots/screen3.png" width="30%">
</p>

---

## 🧠 Architecture

```

UI (Jetpack Compose)
↓
ViewModel (StateFlow)
↓
UseCase
↓
Repository
↓
DataSource (JSON)

```

- MVVM + Clean Architecture
- Fully offline design
- No unnecessary dependencies

---

## 🛠 Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Coroutines / Flow
- AlarmManager
- JSON storage

---

## 📂 Project Structure

```

SilentHub/
├── app/
├── docs/
│   ├── design/
│   ├── claude/
│   ├── SilentHub.md
│   └── content/
├── README.md

```

---

## ⚙️ Development

### Requirements

- Android Studio (latest)
- JDK 17+

### Setup

```bash
git clone https://github.com/bassa-4508/SilentHub.git
cd SilentHub
```

---

## 🧩 Design System

* Minimal UI (Apple-like philosophy)
* Material 3 compliant
* Single accent color
* No unnecessary animation

See:

```
docs/design/
```

---

## 🔐 Privacy

* No internet connection required
* No tracking
* No analytics
* All data stored locally

---

## 🗺 Roadmap

* [x] Project structure
* [ ] Silent notification system
* [ ] Contact management
* [ ] JSON import/export
* [ ] UI polishing

---

## 🤝 Contributing

This is currently a personal project, but PRs are welcome.

---

## 📄 License

MIT License

---

## ⭐ Star This Project

If this project is useful, consider giving it a star ⭐

```

---
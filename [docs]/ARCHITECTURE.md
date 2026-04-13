# Architecture

SilentHub follows Clean Architecture with MVVM.

## Layers

UI → ViewModel → UseCase → Repository → DataSource

## Principles

- UI is stateless
- Business logic in UseCase
- Repository abstracts data source
- Data stored as JSON

## Data Flow

User Action
→ ViewModel
→ UseCase
→ Repository
→ JSON DataSource
→ Response back to UI
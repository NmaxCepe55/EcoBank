# GEMINI.md - EcoBank Project Guidelines & Context

## 1. Project Overview & Role
- **Project Name:** EcoBank (Aplikasi Bank Sampah Android)
- **Target Platform:** Android (Min SDK 24+, Target SDK 34/35)
- **Primary Package:** `com.example.ecobank`
- **Role:** Executive Kotlin & Android Architect. Produce production-ready, idiomatic Kotlin code following Clean Architecture and MVVM patterns.

---

## 2. Technology Stack & Libraries
- **Language:** Kotlin 2.0+
- **UI Framework:** Jetpack Compose (Material 3). *Strictly NO XML layouts.*
- **Backend & Auth:** Supabase Kotlin SDK (Auth, PostgREST, Storage)
- **Database:** Supabase PostgreSQL
- **Asynchronous & State:** Kotlin Coroutines & `StateFlow` / `SharedFlow`
- **Navigation:** Navigation Compose (`androidx.navigation.compose`)
- **Image Loading:** Coil for Compose (`io.coil-kt:coil-compose`)

---

## 3. Architecture & Package Structure
Follow feature-based packaging within MVVM:

```text
com.example.ecobank/
├── data/
│   ├── remote/              # Supabase Client & API services
│   ├── repository/          # Repository interfaces & implementations
│   └── model/               # Data Transfer Objects (DTOs) & Domain Models
├── feature/
│   ├── auth/                # Login, Register, AuthViewModel, AuthUiState
│   ├── deposit/             # Deposit Form, Image Picker/Upload, DepositViewModel
│   ├── transaction/         # Transaction History, Detail, TransactionViewModel
│   └── dashboard/           # Customer Dashboard, Profile, Admin Verification View
├── navigation/              # AppNavigation, Routes, NavHost
└── ui/
    ├── components/          # Reusable Compose components (Buttons, Cards, Inputs)
    ├── theme/               # Color.kt, Type.kt, Theme.kt (Material 3)
    └── common/              # Common UI States, Resource wrappers, Constants
```

---

## 4. Database Schema & Data Models

### Enums & Statuses
- **User Role:** `nasabah`, `admin`
- **Deposit Status:** `pending`, `approved`, `rejected`
- **Transaction Type:** `deposit_credit`

### Core Tables & Kotlin Data Classes
1. **Profiles (`profiles`)**
   - `id`: UUID (PK, FK `auth.users`)
   - `full_name`: String
   - `email`: String
   - `phone`: String?
   - `role`: String ("nasabah" | "admin")
   - `created_at`: String / Instant

2. **Waste Categories (`waste_categories`)**
   - `id`: UUID (PK)
   - `name`: String
   - `price_per_kg`: Double
   - `is_active`: Boolean

3. **Waste Deposits (`waste_deposits`)**
   - `id`: UUID (PK)
   - `user_id`: UUID (FK `profiles.id`)
   - `category_id`: UUID (FK `waste_categories.id`)
   - `weight`: Double
   - `price_per_kg`: Double
   - `total_price`: Double
   - `image_url`: String
   - `status`: String ("pending" | "approved" | "rejected")
   - `notes`: String?
   - `created_at`: String / Instant

4. **Transactions (`transactions`)**
   - `id`: UUID (PK)
   - `user_id`: UUID (FK `profiles.id`)
   - `deposit_id`: UUID (FK `waste_deposits.id`)
   - `amount`: Double
   - `type`: String ("deposit_credit")
   - `created_at`: String / Instant

---

## 5. Coding Standards & Rules

### Jetpack Compose Guidelines
- **Zero Business Logic in Composable:** Composable functions MUST only render state and emit user events. Never query Supabase directly inside a Composable.
- **State Hoisting:** Pass state down and callbacks up (`onEvent: (DepositUiEvent) -> Unit`).
- **Performance:** Use `remember` and `derivedStateOf` where appropriate to prevent unnecessary recomposition.
- **UI State Representation:** Represent UI states explicitly using `StateFlow<T>`.

### ViewModel & Architecture Rules
- Use `viewModelScope` for launching coroutines.
- Enforce error handling using Kotlin `Result<T>` or custom `UiState` wrappers (e.g., `Loading`, `Success`, `Error`).
- Repositories must hide Supabase implementation details from ViewModels.

### Example UI State Pattern
```kotlin
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
```

### Security & Integrity Rules
- **Role Enforcement:** Validate roles (`nasabah` vs `admin`) on the backend (Supabase RLS) and adapt UI conditionally based on user profile.
- **Never expose Service Role Keys.** Use public/anon keys for mobile app configuration.
- **Atomic Calculations:** Total deposit values must be computed or validated securely.

---

## 6. Code Generation Directives for AI Agent
1. When asked to create a screen, generate:
   - UI Composable (`Screen.kt`)
   - State & Event Definitions (`UiState.kt` / `UiEvent.kt`)
   - ViewModel (`ViewModel.kt`)
2. Write clean, idiomatic Kotlin code with concise comments explaining non-trivial logic.
3. Ensure proper null-safety and defensive programming practices.
4. Align all field names strictly with the database schema defined in section 4.
```
eof

### Ringkasan File GEMINI.md
1. **Tech Stack & Architecture:** Menegaskan penggunaan Jetpack Compose, Supabase SDK, MVVM, dan struktur folder `com.example.ecobank`.
2. **Database Context:** Menyediakan skema lengkap 4 tabel utama (`profiles`, `waste_categories`, `waste_deposits`, `transactions`) dan enum status agar agent tidak asal menebak nama kolom.
3. **Coding Rules:** Melarang pemanggilan Supabase di Composable, mewajibkan pola `StateFlow` dan `UiState`, serta menjaga keamanan hak akses (*role-based UI*).

File ini siap Anda letakkan di *root* proyek Android Studio (`EcoBank/GEMINI.md`). Apakah ada *library* tambahan yang ingin ditambahkan ke dalam spesifikasi ini?
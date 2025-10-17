# Koin Annotations Example - Kotlin Multiplatform Project

A Kotlin Multiplatform (KMP) project demonstrating **Clean Architecture** with **Koin Dependency Injection** using **Koin Annotations** and **KSP (Kotlin Symbol Processing)**.

## 🏗️ Architecture Overview

This project follows **Clean Architecture** with modular structure:

```
Presentation 
      ↓
Domain 
      ↑
Data
```

## 🎯 Dependency Flow - The Key Concept

### Module Setup and Dependencies

```
[app] (android)
  |--> [presentation] (android, ViewModel) ----v
  |                                         [domain] (kotlin, Repo-interfaces)    
  |--> [data] (kotlin, Repo-impls) ------------^
```

1. **Presentation** (android module) depends on **Domain** (kotlin module)
2. **Data** (kotlin module) depends on **Domain** (kotlin module)
3. **Presentation and Data do NOT depend on each other**

The `presentation` module does not have transitive access to Koin definitions from `data`:
- `HomeViewModel` can access `GetGreetingsUseCase` (Domain)
- `GetGreetingsUseCase` depends on `GreetingsRepository` interface (Domain)
- But `GreetingsRepositoryImpl` implementation (Data) is **not visible** to `presentation`

### App Module as Composition Root

The **`app` module** aggregates all modules and ensures all Koin definitions are available:

```kotlin
// androidApp/app/build.gradle.kts
dependencies {
    implementation(projects.androidApp.ui.home)              // Presentation layer
    implementation(projects.shared.home.data.implementation) // Data layer
    // Both layers are wired together here
}
```

Only at the `app` module level does Koin have access to all definitions (ViewModels, Use Cases, Repositories, Data Sources) needed for dependency injection.

### Koin Configuration Check Limitation

**Why config check is only in the app module:**

```kotlin
// androidApp/app/build.gradle.kts
ksp {
    arg("KOIN_CONFIG_CHECK", "true") // ✅ Only here
}
```

- **Cannot enable in individual modules**: Each module would fail the check because they don't have all definitions
- **App module checks configuration**: However, `KOIN_CONFIG_CHECK` **fails** to check dependencies in other gradle modules added via `@Module(includes = [...])`

## 🔧 Koin Setup

### Android

```kotlin
// androidApp/app/src/main/kotlin/App.kt
@KoinApplication
object KoinApp

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinApp.startKoin {
            androidContext(this@App)
        }
    }
}
```

### iOS

Not implemented yet (Not sure how to set up Koin, since annotations are used).
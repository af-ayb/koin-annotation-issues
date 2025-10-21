# Koin Annotations Example - Kotlin Multiplatform Project

A Kotlin Multiplatform (KMP) project demonstrating **Clean Architecture** with **Koin Dependency Injection** using **Koin Annotations** and **KSP (Kotlin Symbol Processing)**.

## Architecture Overview

This project follows **Clean Architecture** with modular structure:

```
Presentation 
      ↓
Domain 
      ↑
Data
```

## Dependency Flow

### Module Setup and Dependencies

```
[app] 
  |--> [presentation] (ViewModel) ----v
  |                                         [domain] (Repo-interfaces)    
  |--> [data] (Repo-impls) ------------^
```

1. **Presentation** depends on **Domain**
2. **Data** depends on **Domain** 
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

## Koin Configuration Check Issue

Modules declared in the same Gradle module are discovered, but modules declared in other Gradle modules are not. When ModuleA includes ModuleB (where ModuleA, ModuleB both live in different Gradle modules), the build fails because KSP/Koin cannot find the module metadata for ModuleB — nested/included modules from other Gradle modules are not seen during the KOIN_CONFIG_CHECK validation.

In this project, AppModule should include modules from other Gradle modules:
- GreetingsDataModule (from data)
- HomeUiModule (from presentation)

This is done to consolidate the dependency graph and enable a single, reliable compile-time check of the entire application.

However, if these includes are added, the build fails with:

(Uncomment the `includes` in `AppModule` to reproduce)

```
[ksp] java.lang.IllegalStateException: can't find module metadata for 'example.koin.annotations.home.data.source.GreetingsDataSourceModule'
        in current modules or any meta tags
```

(Because `GreetingsDataSourceModule` is included in `GreetingsDataModule` which is in another Gradle module.)

If the includes is removed, the KOIN_CONFIG_CHECK would not validate those external modules, leading to potential runtime crashes if dependencies are missing (i.e., a missing dependency would only be discovered at runtime initialization instead of compile time, here is the related issue).

This happens even though the KSP-generated module metadata files are present (both ksp/android and ksp/metadata outputs exist for the modules). Disabling `KOIN_CONFIG_CHECK` (setting it to "false") allows the project to compile and run normally. The failure appears to be related to cross-module metadata discovery — each Gradle module is processed independently by KSP and the checker cannot locate metadata from other modules (?)

This looks related to [InsertKoinIO/koin#2163](https://github.com/InsertKoinIO/koin/issues/2163) but occurs with current KSP/Koin annotation setup in a multi-module project.

The issue persists with the latest version 2.2.0.

**Why config check is only in the app module:**

```kotlin
// androidApp/app/build.gradle.kts
ksp {
    arg("KOIN_CONFIG_CHECK", "true") // ✅ Only here
}
```

- **Cannot enable in individual modules**: Each module would fail the check because they don't have all definitions

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
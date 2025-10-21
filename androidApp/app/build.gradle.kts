plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "example.koin.annotations.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        lint.targetSdk = libs.versions.android.targetSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        vectorDrawables.useSupportLibrary = true
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro",
        )
    }

//    sourceSets {
//        getByName("main") {
//            java.srcDir("src/main/kotlin")
//        }
//    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)

    implementation(libs.kotlin.coroutines.core)

    // Jetpack Compose
    implementation(libs.jetpack.compose.runtime)
    implementation(libs.jetpack.compose.preview)
    implementation(libs.jetpack.compose.tooling)
    implementation(platform(libs.jetpack.compose.bom))

    implementation(libs.androidx.activity.compose)

    // koin
    implementation(platform(libs.koin.annotations.bom))
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.annotations)
    implementation(libs.koin.android.compose)
    ksp(libs.koin.ksp)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    // There is no transitive dependency to data modules, so they must be added here for injection
    // UI --> Domain <-- Data
    implementation(projects.shared.home.data.implementation)
    implementation(projects.androidApp.ui.home)

    implementation(projects.shared)
}

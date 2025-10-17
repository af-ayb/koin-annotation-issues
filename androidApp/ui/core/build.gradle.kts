plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "example.koin.annotations.ui.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        vectorDrawables.useSupportLibrary = true
        consumerProguardFiles("proguard-rules.pro")
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(17)
    compilerOptions{
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    }
}

dependencies {
    // Jetpack Compose
    implementation(libs.jetpack.compose.runtime)
    implementation(libs.jetpack.compose.preview)
    implementation(libs.jetpack.compose.tooling)
    implementation(platform(libs.jetpack.compose.bom))

    // koin
    implementation(platform(libs.koin.annotations.bom))
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.annotations)
    implementation(libs.koin.android.compose)
    ksp(libs.koin.ksp)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    api(libs.jetpack.compose.ui)
    api(libs.jetpack.compose.material3)
    api(libs.jetpack.compose.util)
    api(libs.androidx.navigation.compose)
    api(libs.androidx.lifecycle.runtime.compose)
    api(libs.androidx.lifecycle.viewmodel.compose)
}
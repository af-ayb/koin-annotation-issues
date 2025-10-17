import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import kotlin.jvm.java

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
}

kotlin {
    val modulesAccessibleForNative = listOf(
        projects.shared.home.usecase,
        projects.shared.home.entity,
    )

    val modulesWithoutTransitiveDependency = listOf(
        projects.shared.home.data.implementation
    )

    applyDefaultHierarchyTemplate()
    androidTarget()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
            modulesAccessibleForNative.forEach { module ->
                export(module)
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            // koin
            implementation(project.dependencies.platform(libs.koin.annotations.bom))
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.annotations)
            implementation(libs.koin.android.compose)

            modulesAccessibleForNative.forEach { module ->
                api(module)
            }

            modulesWithoutTransitiveDependency.forEach { module ->
                implementation(module)
            }
        }
    }

    jvmToolchain(17)
    compilerOptions{
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    }
}

dependencies {
    add("implementation", project.dependencies.platform(libs.koin.annotations.bom))
    add("kspCommonMainMetadata", libs.koin.ksp)
    add("kspAndroid", libs.koin.ksp)
    add("kspIosX64", libs.koin.ksp)
    add("kspIosArm64", libs.koin.ksp)
    add("kspIosSimulatorArm64", libs.koin.ksp)
}

tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

android {
    namespace = "example.koin.annotations.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        consumerProguardFiles("proguard-rules.pro")
    }
}

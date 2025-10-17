rootProject.name = "KoinAnnotationsExample"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":shared")
include(":androidApp:app")
include(":androidApp:ui:core")
include(":androidApp:ui:home")
include(":shared:home:abstraction")
include(":shared:home:entity")
include(":shared:home:data:implementation")
include(":shared:home:data:source")
include(":shared:home:usecase")

@file:Suppress("UnstableApiUsage")

pluginManagement {
  repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    maven { url = uri("https://jitpack.io") }
    mavenLocal()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    google()
    mavenLocal()
  }
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "preferences"

includeBuild("build-logic")

include(":preferences-android")
include(":preferences-core")

buildscript {
  repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    maven { url = uri("https://jitpack.io") }
    mavenLocal()
  }
  dependencies {
    classpath(libs.plugin.publish)
  }
}

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.detekt) apply false
  alias(libs.plugins.dependencyGuard)
  id("convention-test")
}

dependencyGuard {
  configuration("classpath")
}

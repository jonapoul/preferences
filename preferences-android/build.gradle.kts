import blueprint.core.javaVersionString
import blueprint.recipes.androidLibBlueprint
import blueprint.recipes.kotlinJvmBlueprint

plugins {
  kotlin("android")
  id("com.android.library")
  id("convention-publish")
  id("convention-style")
  id("convention-test")
  id("com.dropbox.dependency-guard")
}

kotlinJvmBlueprint(libs.versions.kotlin)
androidLibBlueprint()

android {
  namespace = "dev.jonpoulton.preferences.android"

  kotlinOptions {
    jvmTarget = javaVersionString()
  }
}

dependencies {
  api(projects.preferencesCore)
  testImplementation(libs.androidx.preferenceKtx)
  testImplementation(libs.test.androidx.coreKtx)
  testImplementation(libs.test.androidx.junitKtx)
  testImplementation(libs.test.robolectric)
  testImplementation(libs.test.truth)
}

dependencyGuard {
  configuration("debugRuntimeClasspath")
  configuration("debugUnitTestRuntimeClasspath")
  configuration("releaseRuntimeClasspath")
  configuration("releaseUnitTestRuntimeClasspath")
}

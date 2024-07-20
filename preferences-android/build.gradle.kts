import blueprint.core.stringProperty

plugins {
  kotlin("android")
  id("convention-android")
  id("convention-publish")
  id("convention-style")
  id("convention-test")
  id("com.dropbox.dependency-guard")
}

android {
  namespace = "dev.jonpoulton.preferences.android"

  kotlinOptions {
    jvmTarget = stringProperty(key = "javaVersion")
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

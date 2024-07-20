import blueprint.core.intProperty

plugins {
  kotlin("jvm")
  id("convention-kotlin")
  id("convention-publish")
  id("convention-style")
  id("convention-test")
  id("com.dropbox.dependency-guard")
}

dependencies {
  api(libs.kotlinx.coroutines.core)
}

kotlin {
  jvmToolchain(jdkVersion = intProperty(key = "javaVersion"))
}

dependencyGuard {
  configuration("runtimeClasspath")
  configuration("testRuntimeClasspath")
}

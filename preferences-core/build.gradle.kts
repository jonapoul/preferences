@file:Suppress("UnusedPrivateProperty")

import blueprint.core.intProperty
import blueprint.recipes.DEFAULT_KOTLIN_FREE_COMPILER_ARGS
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("multiplatform")
  id("convention-publish")
  id("convention-style")
  id("convention-test")
  id("com.dropbox.dependency-guard")
}

kotlin {
  jvm {
    jvmToolchain(intProperty(key = "javaVersion"))
  }

  js(IR) {
    browser()
    nodejs()
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.kotlin.stdlib)
        api(libs.kotlinx.coroutines.core)
      }
    }
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += DEFAULT_KOTLIN_FREE_COMPILER_ARGS
  }
}

dependencyGuard {
  configuration("jsCompileClasspath")
  configuration("jsRuntimeClasspath")
  configuration("jvmCompileClasspath")
  configuration("jvmCompileClasspath")
}

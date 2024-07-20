import blueprint.core.boolProperty
import blueprint.recipes.androidLibBlueprint
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
  kotlin("android")
  id("com.android.library")
  id("convention-kotlin")
}

val libs = the<LibrariesForLibs>()

androidLibBlueprint(
  kotlinVersion = libs.versions.kotlin,
  explicitApi = boolProperty(key = "kotlin.explicitApi"),
)

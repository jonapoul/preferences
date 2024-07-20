import blueprint.core.boolProperty
import blueprint.recipes.kotlinBlueprint
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

kotlinBlueprint(
  kotlinVersion = libs.versions.kotlin,
  explicitApi = boolProperty(key = "kotlin.explicitApi"),
)

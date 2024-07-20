import blueprint.recipes.detektBlueprint
import blueprint.recipes.ktlintBlueprint
import blueprint.recipes.spotlessBlueprint
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

detektBlueprint()

ktlintBlueprint(ktlintCliVersion = libs.versions.ktlint.cli)

spotlessBlueprint()

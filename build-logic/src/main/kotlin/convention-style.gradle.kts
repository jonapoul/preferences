import blueprint.recipes.DetektAll
import blueprint.recipes.detektBlueprint
import blueprint.recipes.ktlintBlueprint
import blueprint.recipes.spotlessBlueprint
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

detektBlueprint(detektAllConfig = DetektAll.Apply(ignoreRelease = true))

ktlintBlueprint(ktlintCliVersion = libs.versions.ktlint.cli)

spotlessBlueprint()

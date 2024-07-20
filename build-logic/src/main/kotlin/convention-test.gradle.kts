import blueprint.recipes.TestVersions
import blueprint.recipes.koverBlueprint
import blueprint.recipes.testBlueprint
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

testBlueprint(
  versions = TestVersions(
    androidxCoreKtx = libs.versions.androidx.test.coreKtx,
    androidxJunit = libs.versions.androidx.test.junitKtx,
    coroutines = libs.versions.kotlinx.coroutines,
    junit = libs.versions.junit,
    kotlin = libs.versions.kotlin,
    robolectric = libs.versions.robolectric,
  )
)

koverBlueprint()

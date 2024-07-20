package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class StringSetOfNullablesPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getStringSetOfNullables("key", default = setOf("a", "b"))
    assertThat(preference1.get()).isEqualTo(setOf("a", "b"))

    val preference2 = androidSharedPreferences.getStringSetOfNullables("key", default = setOf("x", null, "a"))
    assertThat(preference2.get()).isEqualTo(setOf("x", null, "a"))
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getStringSetOfNullables("key", default = emptySet())

    preference.set(setOf("bla", null, "bla"))
    assertThat(preference.get()).isEqualTo(setOf("bla", null))

    runBlocking {
      preference.setAndCommit(emptySet())
      assertThat(preference.get()).isEqualTo(emptySet<String>())
    }
  }
}

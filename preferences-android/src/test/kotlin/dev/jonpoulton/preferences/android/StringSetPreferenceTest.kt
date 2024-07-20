package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class StringSetPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getStringSet("key", default = setOf("a", "b"))
    assertThat(preference1.get()).isEqualTo(setOf("a", "b"))

    val preference2 = androidSharedPreferences.getStringSet("key", default = setOf("x", "y", "a"))
    assertThat(preference2.get()).isEqualTo(setOf("x", "y", "a"))
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getStringSet("key", default = emptySet())

    preference.set(setOf("bla", "bla"))
    assertThat(preference.get()).isEqualTo(setOf("bla"))

    runBlocking {
      preference.setAndCommit(emptySet())
      assertThat(preference.get()).isEqualTo(emptySet<String>())
    }
  }
}

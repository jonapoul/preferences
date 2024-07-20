package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class NullableStringSetPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getNullableStringSet("key", default = setOf("a", "b"))
    assertThat(preference1.get()).isEqualTo(setOf("a", "b"))

    val preference2 = androidSharedPreferences.getNullableStringSet("key", default = null)
    assertThat(preference2.get()).isNull()
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getNullableStringSet("key", default = null)

    preference.set(setOf("bla", "bla"))
    assertThat(preference.get()).isEqualTo(setOf("bla"))

    runBlocking {
      preference.setAndCommit(emptySet())
      assertThat(preference.get()).isEqualTo(emptySet<String>())
    }
  }

  @Test
  fun testSettingNullValues() {
    val preference = androidSharedPreferences.getNullableStringSet("key", default = emptySet())

    preference.set(null)
    assertThat(preference.get()).isEqualTo(emptySet<String>())
    assertThat(preference.isSet()).isFalse()

    runBlocking {
      preference.setAndCommit(null)
      assertThat(preference.get()).isEqualTo(emptySet<String>())
      assertThat(preference.isSet()).isFalse()
    }
  }
}

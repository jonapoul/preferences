package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class NullableStringSetOfNullablesPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getNullableStringSetOfNullables("key", default = setOf("a", "b"))
    assertThat(preference1.get()).isEqualTo(setOf("a", "b"))

    val preference2 = androidSharedPreferences.getNullableStringSetOfNullables("key", default = setOf("x", null, "a"))
    assertThat(preference2.get()).isEqualTo(setOf("x", null, "a"))

    val preference3 = androidSharedPreferences.getNullableStringSetOfNullables("key", default = null)
    assertThat(preference3.get()).isNull()
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getNullableStringSetOfNullables("key", default = null)

    preference.set(setOf("bla", null, "bla"))
    assertThat(preference.get()).isEqualTo(setOf("bla", null))

    runBlocking {
      preference.setAndCommit(emptySet())
      assertThat(preference.get()).isEqualTo(emptySet<String>())
    }
  }

  @Test
  fun testSettingNullValues() {
    val preference = androidSharedPreferences.getNullableStringSetOfNullables("key", default = emptySet())

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

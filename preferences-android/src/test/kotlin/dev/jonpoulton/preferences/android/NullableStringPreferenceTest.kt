package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class NullableStringPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getNullableString("key", default = "abc")
    assertThat(preference1.get()).isEqualTo("abc")

    val preference2 = androidSharedPreferences.getNullableString("key", default = null)
    assertThat(preference2.get()).isNull()
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getNullableString("key", default = null)

    preference.set("--")
    assertThat(preference.get()).isEqualTo("--")

    runBlocking {
      preference.setAndCommit("x")
      assertThat(preference.get()).isEqualTo("x")
    }
  }

  @Test
  fun testSettingNullValues() {
    val preference = androidSharedPreferences.getNullableString("key", default = "default")

    preference.set(null)
    assertThat(preference.get()).isEqualTo("default")
    assertThat(preference.isSet()).isFalse()

    runBlocking {
      preference.setAndCommit(null)
      assertThat(preference.get()).isEqualTo("default")
      assertThat(preference.isSet()).isFalse()
    }
  }
}

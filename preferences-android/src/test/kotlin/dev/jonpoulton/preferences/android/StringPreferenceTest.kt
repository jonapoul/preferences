package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class StringPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getString("key", default = "abc")
    assertThat(preference1.get()).isEqualTo("abc")

    val preference2 = androidSharedPreferences.getString("key", default = "@#$")
    assertThat(preference2.get()).isEqualTo("@#$")
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getString("key", default = "")

    preference.set("--")
    assertThat(preference.get()).isEqualTo("--")

    runBlocking {
      preference.setAndCommit("x")
      assertThat(preference.get()).isEqualTo("x")
    }
  }
}

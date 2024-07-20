package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class IntPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getInt("key", default = 1)
    assertThat(preference1.get()).isEqualTo(1)

    val preference2 = androidSharedPreferences.getInt("key", default = 555)
    assertThat(preference2.get()).isEqualTo(555)
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getInt("key", default = 0)

    preference.set(42)
    assertThat(preference.get()).isEqualTo(42)

    runBlocking {
      preference.setAndCommit(43)
      assertThat(preference.get()).isEqualTo(43)
    }
  }
}

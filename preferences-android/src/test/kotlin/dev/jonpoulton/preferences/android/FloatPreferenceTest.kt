package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class FloatPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getFloat("key", default = 0f)
    assertThat(preference1.get()).isEqualTo(0f)

    val preference2 = androidSharedPreferences.getFloat("key", default = 1.1f)
    assertThat(preference2.get()).isEqualTo(1.1f)
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getFloat("key", default = 0f)

    preference.set(23f)
    assertThat(preference.get()).isEqualTo(23f)

    runBlocking {
      preference.setAndCommit(100f)
      assertThat(preference.get()).isEqualTo(100f)
    }
  }
}

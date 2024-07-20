package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class BooleanPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getBoolean("key", default = false)
    assertThat(preference1.get()).isFalse()

    val preference2 = androidSharedPreferences.getBoolean("key", default = true)
    assertThat(preference2.get()).isTrue()
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getBoolean("key", default = false)

    preference.set(true)
    assertThat(preference.get()).isTrue()

    runBlocking {
      preference.setAndCommit(false)
      assertThat(preference.get()).isFalse()
    }
  }
}

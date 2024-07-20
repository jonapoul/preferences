package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class LongPreferenceTest : BaseTest() {
  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getLong("key", default = 430)
    Truth.assertThat(preference1.get()).isEqualTo(430)

    val preference2 = androidSharedPreferences.getLong("key", default = 3)
    Truth.assertThat(preference2.get()).isEqualTo(3)
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getLong("key", default = 0)

    preference.set(28)
    Truth.assertThat(preference.get()).isEqualTo(28)

    runBlocking {
      preference.setAndCommit(67)
      Truth.assertThat(preference.get()).isEqualTo(67)
    }
  }
}

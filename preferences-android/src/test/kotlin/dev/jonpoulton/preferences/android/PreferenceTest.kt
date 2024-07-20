package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class PreferenceTest : BaseTest() {
  @Test
  fun testIsSet() {
    val preference = androidSharedPreferences.getBoolean("key", default = true)

    assertThat(preference.isSet()).isFalse()

    preference.set(true)

    assertThat(preference.isSet()).isTrue()
  }

  @Test
  fun testDelete() {
    val preference = androidSharedPreferences.getString("key", default = "")
    preference.set("some value")

    preference.delete()

    assertThat(preference.isSet()).isFalse()
    assertThat(preference.get()).isEmpty()
  }

  @Test
  fun testDeleteAndCommit() {
    val preference = androidSharedPreferences.getLong("key", default = 0)
    preference.set(340L)

    runBlocking {
      preference.deleteAndCommit()
    }

    assertThat(preference.isSet()).isFalse()
    assertThat(preference.get()).isEqualTo(0)
  }

  @Test
  fun testClear() {
    val preference1 = androidSharedPreferences.getInt("key1", default = 0).apply { set(10) }
    val preference2 = androidSharedPreferences.getString("key2", default = "").apply { set("xyz") }

    androidSharedPreferences.clear()

    assertThat(preference1.isSet()).isFalse()
    assertThat(preference2.isSet()).isFalse()
  }

  @Test
  fun testMultipleFlows() {
    val preference = androidSharedPreferences.getLong("key", default = -1)

    runBlocking {
      assertThat(preference.asFlow().first()).isEqualTo(-1)

      preference.set(10)
      assertThat(preference.asFlow().first()).isEqualTo(10)

      preference.set(20)
      assertThat(preference.asFlow().first()).isEqualTo(20)
      assertThat(preference.asFlow().first()).isEqualTo(20)
      assertThat(preference.asFlow().first()).isEqualTo(20)

      preference.delete()
      assertThat(preference.asFlow().first()).isEqualTo(-1)
    }
  }

  @Test
  fun testFlowConflatedBehavior() {
    val preference = androidSharedPreferences.getFloat("key", default = 0.5f)

    preference.set(20f)
    preference.set(30f)
    preference.set(50f)

    runBlocking {
      assertThat(preference.asFlow().first()).isEqualTo(50f)
    }
  }
}

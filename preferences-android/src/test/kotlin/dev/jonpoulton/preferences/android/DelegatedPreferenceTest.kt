package dev.jonpoulton.preferences.android

import dev.jonpoulton.preferences.core.Preferences
import dev.jonpoulton.preferences.core.getValue
import dev.jonpoulton.preferences.core.setValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class DelegatedPreferenceTest : BaseTest() {
  private class MyTestPreferences(prefs: Preferences) {
    var int by prefs.getInt(key = "int", default = 0)
    var nullableString by prefs.getNullableString(key = "nullableString", default = null)

    val immutableFloat by prefs.getFloat(key = "float", default = 0f)
  }

  private lateinit var preferences: MyTestPreferences

  @Before
  fun before() {
    preferences = MyTestPreferences(androidSharedPreferences)
  }

  @Test
  fun `Get and set int`() {
    assertEquals(expected = 0, actual = preferences.int)
    preferences.int = 123
    assertEquals(expected = 123, actual = preferences.int)
  }

  @Test
  fun `Get and set nullable string`() {
    assertEquals(expected = null, actual = preferences.nullableString)
    preferences.nullableString = "abc123"
    assertEquals(expected = "abc123", actual = preferences.nullableString)
  }

  @Test
  fun `Get float`() {
    assertEquals(expected = 0f, actual = preferences.immutableFloat)
  }
}

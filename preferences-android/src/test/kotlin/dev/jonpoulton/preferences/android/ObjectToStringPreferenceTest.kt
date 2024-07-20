package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import dev.jonpoulton.preferences.core.SimpleStringSerializer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class ObjectToStringPreferenceTest : BaseTest() {
  private data class TestObject(val int: Int, val string: String) {
    override fun toString(): String = "$int,$string"
  }

  private fun parseTestObject(string: String): TestObject {
    val split = string.split(",")
    return TestObject(
      int = split[0].toInt(),
      string = split[1],
    )
  }

  private val serializer = SimpleStringSerializer(::parseTestObject)

  @Test
  fun testDefaultValues() {
    val default = TestObject(int = 123, string = "abc")
    val preference = androidSharedPreferences.getObject("key", serializer, default)
    assertThat(preference.get()).isEqualTo(default)
  }

  @Test
  fun testSettingValues() {
    val default = TestObject(int = 123, string = "abc")
    val preference = androidSharedPreferences.getObject(key = "key", serializer, default)

    val newValue = TestObject(int = 456, string = "def")
    preference.set(newValue)
    assertThat(preference.get()).isEqualTo(newValue)
  }
}

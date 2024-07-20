package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import dev.jonpoulton.preferences.core.NullableStringSerializer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class NullableObjectPreferenceTest : BaseTest() {
  private class TestObject(val id: Int)

  private val serializer = object : NullableStringSerializer<TestObject> {
    override fun deserialize(value: String?) = value?.let { TestObject(it.toInt()) }
    override fun serialize(value: TestObject?) = value?.id?.toString()
  }

  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getNullableObject("key", serializer, default = TestObject(0))
    assertThat(preference1.get()?.id).isEqualTo(0)

    val preference2 = androidSharedPreferences.getNullableObject("key", serializer, default = null)
    assertThat(preference2.get()).isNull()
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getNullableObject("key", serializer, default = TestObject(-1))

    preference.set(TestObject(100))
    assertThat(preference.get()?.id).isEqualTo(100)

    runBlocking {
      preference.setAndCommit(TestObject(2000))
      assertThat(preference.get()?.id).isEqualTo(2000)
    }
  }

  @Test
  fun testSettingNullValues() {
    val preference = androidSharedPreferences.getNullableObject("key", serializer, default = TestObject(-1))

    preference.set(null)
    assertThat(preference.get()?.id).isEqualTo(-1)
    assertThat(preference.isSet()).isFalse()

    runBlocking {
      preference.setAndCommit(null)
      assertThat(preference.get()?.id).isEqualTo(-1)
      assertThat(preference.isSet()).isFalse()
    }
  }
}

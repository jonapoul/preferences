package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import dev.jonpoulton.preferences.core.IntSerializer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class ObjectPreferenceTest : BaseTest() {
  private class TestObject(val id: Int)

  private val serializer = object : IntSerializer<TestObject> {
    override fun deserialize(value: Int) = TestObject(value)
    override fun serialize(value: TestObject) = value.id
  }

  @Test
  fun testDefaultValues() {
    val preference1 = androidSharedPreferences.getObject("key", serializer, default = TestObject(0))
    assertThat(preference1.get().id).isEqualTo(0)

    val preference2 = androidSharedPreferences.getObject("key", serializer, default = TestObject(1))
    assertThat(preference2.get().id).isEqualTo(1)
  }

  @Test
  fun testSettingValues() {
    val preference = androidSharedPreferences.getObject("key", serializer, default = TestObject(-1))

    preference.set(TestObject(100))
    assertThat(preference.get().id).isEqualTo(100)

    runBlocking {
      preference.setAndCommit(TestObject(2000))
      assertThat(preference.get().id).isEqualTo(2000)
    }
  }
}

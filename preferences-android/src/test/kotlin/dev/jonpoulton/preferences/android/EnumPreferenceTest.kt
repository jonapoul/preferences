package dev.jonpoulton.preferences.android

import com.google.common.truth.Truth.assertThat
import dev.jonpoulton.preferences.core.enumNameSerializer
import dev.jonpoulton.preferences.core.enumOrdinalSerializer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class EnumPreferenceTest : BaseTest() {
  private enum class TestEnum { A, B, C }

  @Test
  fun testDefaultValuesByName() {
    val nameSerializer = enumNameSerializer<TestEnum>()

    val preference1 = androidSharedPreferences.getObject("key1", nameSerializer, default = TestEnum.A)
    assertThat(preference1.get()).isEqualTo(TestEnum.A)

    val preference2 = androidSharedPreferences.getObject("key2", nameSerializer, default = TestEnum.C)
    assertThat(preference2.get()).isEqualTo(TestEnum.C)
  }

  @Test
  fun testSettingValuesByName() {
    val nameSerializer = enumNameSerializer<TestEnum>()
    val preference = androidSharedPreferences.getObject("key", nameSerializer, default = TestEnum.A)

    preference.set(TestEnum.B)
    assertThat(preference.get()).isEqualTo(TestEnum.B)

    runBlocking {
      preference.setAndCommit(TestEnum.C)
      assertThat(preference.get()).isEqualTo(TestEnum.C)
    }
  }

  @Test
  fun testDefaultValuesByOrdinal() {
    val nameSerializer = enumOrdinalSerializer<TestEnum>()

    val preference1 = androidSharedPreferences.getObject("key1", nameSerializer, default = TestEnum.A)
    assertThat(preference1.get()).isEqualTo(TestEnum.A)

    val preference2 = androidSharedPreferences.getObject("key2", nameSerializer, default = TestEnum.C)
    assertThat(preference2.get()).isEqualTo(TestEnum.C)
  }

  @Test
  fun testSettingValuesByOrdinal() {
    val nameSerializer = enumOrdinalSerializer<TestEnum>()
    val preference = androidSharedPreferences.getObject("key", nameSerializer, default = TestEnum.A)

    preference.set(TestEnum.B)
    assertThat(preference.get()).isEqualTo(TestEnum.B)

    runBlocking {
      preference.setAndCommit(TestEnum.C)
      assertThat(preference.get()).isEqualTo(TestEnum.C)
    }
  }
}

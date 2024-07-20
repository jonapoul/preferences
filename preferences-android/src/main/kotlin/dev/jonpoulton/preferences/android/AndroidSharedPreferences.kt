package dev.jonpoulton.preferences.android

import android.content.SharedPreferences
import dev.jonpoulton.preferences.android.internal.BooleanPreference
import dev.jonpoulton.preferences.android.internal.FloatPreference
import dev.jonpoulton.preferences.android.internal.IntPreference
import dev.jonpoulton.preferences.android.internal.KeyFlow
import dev.jonpoulton.preferences.android.internal.LongPreference
import dev.jonpoulton.preferences.android.internal.NullableObjectPreference
import dev.jonpoulton.preferences.android.internal.NullableStringPreference
import dev.jonpoulton.preferences.android.internal.NullableStringSetOfNullablesPreference
import dev.jonpoulton.preferences.android.internal.NullableStringSetPreference
import dev.jonpoulton.preferences.android.internal.ObjectPreference
import dev.jonpoulton.preferences.android.internal.StringPreference
import dev.jonpoulton.preferences.android.internal.StringSetOfNullablesPreference
import dev.jonpoulton.preferences.android.internal.StringSetPreference
import dev.jonpoulton.preferences.core.Preference
import dev.jonpoulton.preferences.core.Preferences
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class AndroidSharedPreferences @JvmOverloads constructor(
  val sharedPreferences: SharedPreferences,
  val coroutineContext: CoroutineContext = Dispatchers.IO,
) : Preferences {
  private val keyFlow: KeyFlow = sharedPreferences.keyFlow

  override fun contains(key: String): Boolean = sharedPreferences.contains(key)

  override fun getInt(key: String, default: Int): Preference<Int> =
    IntPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getLong(key: String, default: Long): Preference<Long> =
    LongPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getFloat(key: String, default: Float): Preference<Float> =
    FloatPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getBoolean(key: String, default: Boolean): Preference<Boolean> =
    BooleanPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getString(key: String, default: String): Preference<String> =
    StringPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getNullableString(key: String, default: String?): Preference<String?> =
    NullableStringPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getStringSet(key: String, default: Set<String>): Preference<Set<String>> =
    StringSetPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getNullableStringSet(key: String, default: Set<String>?): Preference<Set<String>?> =
    NullableStringSetPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getStringSetOfNullables(key: String, default: Set<String?>): Preference<Set<String?>> =
    StringSetOfNullablesPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun getNullableStringSetOfNullables(key: String, default: Set<String?>?): Preference<Set<String?>?> =
    NullableStringSetOfNullablesPreference(key, default, keyFlow, sharedPreferences, coroutineContext)

  override fun <R : Any, T : Any> getObject(
    key: String,
    serializer: Preferences.Serializer<R, T>,
    default: T,
  ): Preference<T> = ObjectPreference(key, serializer, default, keyFlow, sharedPreferences, coroutineContext)

  override fun <R : Any, T : Any> getNullableObject(
    key: String,
    serializer: Preferences.NullableSerializer<R, T>,
    default: T?,
  ): Preference<T?> =
    NullableObjectPreference(key, serializer, default, keyFlow, sharedPreferences, coroutineContext)

  override fun clear() = sharedPreferences.edit().clear().apply()
}

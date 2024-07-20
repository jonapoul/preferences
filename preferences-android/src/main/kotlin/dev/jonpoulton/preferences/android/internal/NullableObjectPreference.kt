package dev.jonpoulton.preferences.android.internal

import android.annotation.SuppressLint
import android.content.SharedPreferences
import dev.jonpoulton.preferences.android.put
import dev.jonpoulton.preferences.core.NullableStringSerializer
import dev.jonpoulton.preferences.core.NullableStringSetSerializer
import dev.jonpoulton.preferences.core.Preferences
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class NullableObjectPreference<R : Any, T : Any>(
  override val key: String,
  private val serializer: Preferences.NullableSerializer<R, T>,
  override val default: T?,
  keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : BasePreference<T?>(key, keyFlow, sharedPreferences, coroutineContext) {
  private val serializedDefault = serializer.serialize(default)

  @Suppress("UNCHECKED_CAST")
  override fun get(): T? = when (serializer) {
    is NullableStringSerializer<*> -> serializer.deserialize(
      sharedPreferences.getString(key, null) ?: serializedDefault as? String,
    )

    is NullableStringSetSerializer<*> -> serializer.deserialize(
      sharedPreferences.getStringSet(key, null) ?: serializedDefault as? Set<String>,
    )
  } as? T? ?: default

  override fun set(value: T?) {
    if (value == null) {
      delete()
    } else {
      sharedPreferences
        .edit()
        .put(key, value, serializer)
        .apply()
    }
  }

  @SuppressLint("ApplySharedPref")
  override suspend fun setAndCommit(value: T?) = withContext(coroutineContext) {
    if (value == null) {
      deleteAndCommit()
    } else {
      sharedPreferences
        .edit()
        .put(key, value, serializer)
        .commit()
    }
  }
}

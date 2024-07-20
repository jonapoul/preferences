package dev.jonpoulton.preferences.android.internal

import android.content.SharedPreferences
import dev.jonpoulton.preferences.android.put
import dev.jonpoulton.preferences.core.BooleanSerializer
import dev.jonpoulton.preferences.core.FloatSerializer
import dev.jonpoulton.preferences.core.IntSerializer
import dev.jonpoulton.preferences.core.LongSerializer
import dev.jonpoulton.preferences.core.Preferences
import dev.jonpoulton.preferences.core.StringSerializer
import dev.jonpoulton.preferences.core.StringSetSerializer
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class ObjectPreference<R : Any, T : Any>(
  override val key: String,
  private val serializer: Preferences.Serializer<R, T>,
  override val default: T,
  keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : BasePreference<T>(key, keyFlow, sharedPreferences, coroutineContext) {
  private val serializedDefault = serializer.serialize(default)

  @Suppress("UNCHECKED_CAST", "CyclomaticComplexMethod")
  override fun get(): T = when (serializer) {
    is BooleanSerializer<*> ->
      serializer.deserialize(sharedPreferences.getBoolean(key, serializedDefault as Boolean))

    is FloatSerializer<*> ->
      serializer.deserialize(sharedPreferences.getFloat(key, serializedDefault as Float))

    is IntSerializer<*> ->
      serializer.deserialize(sharedPreferences.getInt(key, serializedDefault as Int))

    is LongSerializer<*> ->
      serializer.deserialize(sharedPreferences.getLong(key, serializedDefault as Long))

    is StringSerializer<*> ->
      serializer.deserialize(sharedPreferences.getString(key, null) ?: serializedDefault as String)

    is StringSetSerializer<*> ->
      serializer.deserialize(sharedPreferences.getStringSet(key, null) ?: serializedDefault as Set<String>)
  } as? T? ?: default

  override fun set(value: T) {
    sharedPreferences
      .edit()
      .put(key, value, serializer)
      .apply()
  }

  override suspend fun setAndCommit(value: T) = withContext(coroutineContext) {
    sharedPreferences
      .edit()
      .put(key, value, serializer)
      .commit()
  }
}

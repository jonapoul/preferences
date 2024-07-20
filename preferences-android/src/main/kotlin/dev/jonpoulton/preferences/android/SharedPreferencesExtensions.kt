package dev.jonpoulton.preferences.android

import android.content.SharedPreferences
import dev.jonpoulton.preferences.android.internal.KeyFlow
import dev.jonpoulton.preferences.core.BooleanSerializer
import dev.jonpoulton.preferences.core.FloatSerializer
import dev.jonpoulton.preferences.core.IntSerializer
import dev.jonpoulton.preferences.core.LongSerializer
import dev.jonpoulton.preferences.core.NullableStringSerializer
import dev.jonpoulton.preferences.core.NullableStringSetSerializer
import dev.jonpoulton.preferences.core.Preferences
import dev.jonpoulton.preferences.core.StringSerializer
import dev.jonpoulton.preferences.core.StringSetSerializer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

val SharedPreferences.keyFlow: KeyFlow
  get() = callbackFlow {
    // key can be null when preferences are cleared on API Level 30+
    val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key: String? -> trySend(key) }
    registerOnSharedPreferenceChangeListener(listener)
    awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
  }

fun <R : Any, T : Any> SharedPreferences.Editor.put(
  key: String,
  value: T,
  serializer: Preferences.Serializer<R, T>,
): SharedPreferences.Editor = when (serializer) {
  is BooleanSerializer -> putBoolean(key, serializer.serialize(value))
  is FloatSerializer -> putFloat(key, serializer.serialize(value))
  is IntSerializer -> putInt(key, serializer.serialize(value))
  is LongSerializer -> putLong(key, serializer.serialize(value))
  is StringSerializer -> putString(key, serializer.serialize(value))
  is StringSetSerializer -> putStringSet(key, serializer.serialize(value))
}

fun <R : Any, T : Any> SharedPreferences.Editor.put(
  key: String,
  value: T?,
  serializer: Preferences.NullableSerializer<R, T>,
): SharedPreferences.Editor = when (serializer) {
  is NullableStringSerializer -> putString(key, serializer.serialize(value))
  is NullableStringSetSerializer -> putStringSet(key, serializer.serialize(value))
}

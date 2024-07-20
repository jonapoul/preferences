package dev.jonpoulton.preferences.android.internal

import android.content.SharedPreferences
import dev.jonpoulton.preferences.core.Preference
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal abstract class BasePreference<T>(
  override val key: String,
  private val keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : Preference<T> {
  override fun isSet() = sharedPreferences.contains(key)

  override fun delete() = sharedPreferences.edit().remove(key).apply()

  override suspend fun deleteAndCommit() =
    withContext(coroutineContext) { sharedPreferences.edit().remove(key).commit() }

  override fun asFlow() =
    keyFlow
      .filter { it == key || it == null } // null means preferences were cleared (Android R+ exclusive behavior)
      .onStart { emit("first load trigger") }
      .map { get() }
      .conflate()
}

package dev.jonpoulton.preferences.android.internal

import android.content.SharedPreferences
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class StringSetPreference(
  override val key: String,
  override val default: Set<String>,
  keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : BasePreference<Set<String>>(key, keyFlow, sharedPreferences, coroutineContext) {
  override fun get(): Set<String> = checkNotNull(sharedPreferences.getStringSet(key, default))

  override fun set(value: Set<String>) = sharedPreferences.edit().putStringSet(key, value).apply()

  override suspend fun setAndCommit(value: Set<String>) = withContext(coroutineContext) {
    sharedPreferences.edit().putStringSet(key, value).commit()
  }
}

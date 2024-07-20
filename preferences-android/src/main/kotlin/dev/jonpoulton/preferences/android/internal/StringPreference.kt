package dev.jonpoulton.preferences.android.internal

import android.content.SharedPreferences
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class StringPreference(
  override val key: String,
  override val default: String,
  keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : BasePreference<String>(key, keyFlow, sharedPreferences, coroutineContext) {
  override fun get() = checkNotNull(sharedPreferences.getString(key, default))

  override fun set(value: String) = sharedPreferences.edit().putString(key, value).apply()

  override suspend fun setAndCommit(value: String) = withContext(coroutineContext) {
    sharedPreferences.edit().putString(key, value).commit()
  }
}

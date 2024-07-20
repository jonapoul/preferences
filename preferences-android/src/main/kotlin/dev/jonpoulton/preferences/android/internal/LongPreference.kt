package dev.jonpoulton.preferences.android.internal

import android.content.SharedPreferences
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class LongPreference(
  override val key: String,
  override val default: Long,
  keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : BasePreference<Long>(key, keyFlow, sharedPreferences, coroutineContext) {
  override fun get() = sharedPreferences.getLong(key, default)

  override fun set(value: Long) = sharedPreferences.edit().putLong(key, value).apply()

  override suspend fun setAndCommit(value: Long) = withContext(coroutineContext) {
    sharedPreferences.edit().putLong(key, value).commit()
  }
}

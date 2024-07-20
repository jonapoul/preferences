package dev.jonpoulton.preferences.android.internal

import android.annotation.SuppressLint
import android.content.SharedPreferences
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class NullableStringPreference(
  override val key: String,
  override val default: String?,
  keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : BasePreference<String?>(key, keyFlow, sharedPreferences, coroutineContext) {
  override fun get() = sharedPreferences.getString(key, default)

  override fun set(value: String?) {
    if (value == null) {
      delete()
    } else {
      sharedPreferences.edit().putString(key, value).apply()
    }
  }

  @SuppressLint("ApplySharedPref")
  override suspend fun setAndCommit(value: String?) = withContext(coroutineContext) {
    if (value == null) {
      deleteAndCommit()
    } else {
      sharedPreferences.edit().putString(key, value).commit()
    }
  }
}

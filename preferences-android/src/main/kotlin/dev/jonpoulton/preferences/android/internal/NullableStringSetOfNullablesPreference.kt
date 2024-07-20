package dev.jonpoulton.preferences.android.internal

import android.annotation.SuppressLint
import android.content.SharedPreferences
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class NullableStringSetOfNullablesPreference(
  override val key: String,
  override val default: Set<String?>?,
  keyFlow: KeyFlow,
  private val sharedPreferences: SharedPreferences,
  private val coroutineContext: CoroutineContext,
) : BasePreference<Set<String?>?>(key, keyFlow, sharedPreferences, coroutineContext) {
  override fun get(): Set<String?>? = sharedPreferences.getStringSet(key, default)

  override fun set(value: Set<String?>?) {
    if (value == null) {
      delete()
    } else {
      sharedPreferences
        .edit()
        .putStringSet(key, value)
        .apply()
    }
  }

  @SuppressLint("ApplySharedPref")
  override suspend fun setAndCommit(value: Set<String?>?) = withContext(coroutineContext) {
    if (value == null) {
      deleteAndCommit()
    } else {
      sharedPreferences
        .edit()
        .putStringSet(key, value)
        .commit()
    }
  }
}

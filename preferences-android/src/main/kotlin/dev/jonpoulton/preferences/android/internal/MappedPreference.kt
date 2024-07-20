package dev.jonpoulton.preferences.android.internal

import dev.jonpoulton.preferences.core.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MappedPreference<T, R>(
  private val preference: Preference<T>,
  private val mapper: (T) -> R,
  private val reverse: (R) -> T,
) : Preference<R> {
  override val key: String get() = preference.key

  override val default: R get() = mapper(preference.default)

  override fun get(): R = mapper(preference.get())

  override fun set(value: R) = preference.set(reverse(value))

  override suspend fun setAndCommit(value: R): Boolean = preference.setAndCommit(reverse(value))

  override fun isSet(): Boolean = preference.isSet()

  override fun delete() = preference.delete()

  override suspend fun deleteAndCommit(): Boolean = preference.deleteAndCommit()

  override fun asFlow(): Flow<R> = preference.asFlow().map { mapper(it) }
}

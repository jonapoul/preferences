package dev.jonpoulton.preferences.core

import kotlinx.coroutines.flow.Flow

interface Preference<T> {
  val key: String
  val default: T
  fun get(): T
  fun set(value: T)
  fun isSet(): Boolean
  fun delete()
  fun asFlow(): Flow<T>
  suspend fun setAndCommit(value: T): Boolean
  suspend fun deleteAndCommit(): Boolean
}

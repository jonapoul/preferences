package dev.jonpoulton.preferences.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.reflect.KProperty

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

operator fun <T> Preference<T>.getValue(thisObj: Any?, property: KProperty<*>): T = get()

operator fun <T> Preference<T>.setValue(thisObj: Any?, property: KProperty<*>, value: T) = set(value)

fun <T> Preference<T>.asStateFlow(
  scope: CoroutineScope,
  started: SharingStarted = SharingStarted.Eagerly,
): StateFlow<T> = asFlow().stateIn(scope, started, default)

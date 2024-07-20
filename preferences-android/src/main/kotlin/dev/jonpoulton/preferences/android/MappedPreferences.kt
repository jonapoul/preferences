package dev.jonpoulton.preferences.android

import dev.jonpoulton.preferences.android.internal.MappedPreference
import dev.jonpoulton.preferences.core.Preference

/**
 * Returns the preference parameters after applying the [mapper] to convert the output
 * and [reverse] to convert the input back to original values.
 */
fun <T, R> Preference<T>.map(
  mapper: (T) -> R,
  reverse: (R) -> T,
): Preference<R> = MappedPreference(this, mapper, reverse)

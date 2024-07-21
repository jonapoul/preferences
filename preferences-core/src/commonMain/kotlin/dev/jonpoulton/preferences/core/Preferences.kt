package dev.jonpoulton.preferences.core

interface Preferences {
  fun clear()
  fun contains(key: String): Boolean
  fun getBoolean(key: String, default: Boolean): Preference<Boolean>
  fun getFloat(key: String, default: Float): Preference<Float>
  fun getInt(key: String, default: Int): Preference<Int>
  fun getLong(key: String, default: Long): Preference<Long>
  fun getNullableString(key: String, default: String?): Preference<String?>
  fun getNullableStringSet(key: String, default: Set<String>?): Preference<Set<String>?>
  fun getNullableStringSetOfNullables(key: String, default: Set<String?>?): Preference<Set<String?>?>
  fun getString(key: String, default: String): Preference<String>
  fun getStringSet(key: String, default: Set<String>): Preference<Set<String>>
  fun getStringSetOfNullables(key: String, default: Set<String?>): Preference<Set<String?>>

  fun <R : Any, T : Any> getObject(
    key: String,
    serializer: Serializer<R, T>,
    default: T,
  ): Preference<T>

  fun <R : Any, T : Any> getNullableObject(
    key: String,
    serializer: NullableSerializer<R, T>,
    default: T?,
  ): Preference<T?>

  sealed interface Serializer<R : Any, T : Any> {
    fun deserialize(value: R): T
    fun serialize(value: T): R
  }

  sealed interface NullableSerializer<R : Any, T : Any> {
    fun deserialize(value: R?): T?
    fun serialize(value: T?): R?
  }
}

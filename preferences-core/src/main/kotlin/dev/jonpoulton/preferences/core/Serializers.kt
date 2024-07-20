package dev.jonpoulton.preferences.core

interface BooleanSerializer<T : Any> : Preferences.Serializer<Boolean, T>
interface FloatSerializer<T : Any> : Preferences.Serializer<Float, T>
interface IntSerializer<T : Any> : Preferences.Serializer<Int, T>
interface LongSerializer<T : Any> : Preferences.Serializer<Long, T>
interface StringSerializer<T : Any> : Preferences.Serializer<String, T>
interface StringSetSerializer<T : Any> : Preferences.Serializer<Set<String>, T>

interface NullableStringSerializer<T : Any> : Preferences.NullableSerializer<String, T>
interface NullableStringSetSerializer<T : Any> : Preferences.NullableSerializer<Set<String>, T>

inline fun <reified E : Enum<E>> enumOrdinalSerializer(): IntSerializer<E> = object : IntSerializer<E> {
  override fun deserialize(value: Int): E = enumValues<E>()[value]
  override fun serialize(value: E): Int = value.ordinal
}

inline fun <reified E : Enum<E>> enumNameSerializer(): StringSerializer<E> = object : StringSerializer<E> {
  override fun deserialize(value: String): E = enumValueOf<E>(value)
  override fun serialize(value: E): String = value.name
}

fun interface SimpleStringSerializer<T : Any> : StringSerializer<T> {
  override fun serialize(value: T): String = value.toString()
}

fun interface SimpleNullableStringSerializer<T : Any> : NullableStringSerializer<T> {
  override fun serialize(value: T?): String? = value?.toString()
}

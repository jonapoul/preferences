package dev.jonpoulton.preferences.core

interface BooleanSerializer<Serialized : Any> : Preferences.Serializer<Boolean, Serialized>
interface FloatSerializer<Serialized : Any> : Preferences.Serializer<Float, Serialized>
interface IntSerializer<Serialized : Any> : Preferences.Serializer<Int, Serialized>
interface LongSerializer<Serialized : Any> : Preferences.Serializer<Long, Serialized>
interface StringSerializer<Serialized : Any> : Preferences.Serializer<String, Serialized>
interface StringSetSerializer<Serialized : Any> : Preferences.Serializer<Set<String>, Serialized>

interface NullableStringSerializer<Serialized : Any> : Preferences.NullableSerializer<String, Serialized>
interface NullableStringSetSerializer<Serialized : Any> : Preferences.NullableSerializer<Set<String>, Serialized>

inline fun <reified E : Enum<E>> enumOrdinalSerializer(): IntSerializer<E> = object : IntSerializer<E> {
  override fun deserialize(value: Int): E = enumValues<E>()[value]
  override fun serialize(value: E): Int = value.ordinal
}

inline fun <reified E : Enum<E>> enumNameSerializer(): StringSerializer<E> = object : StringSerializer<E> {
  override fun deserialize(value: String): E = enumValueOf<E>(value)
  override fun serialize(value: E): String = value.name
}

fun interface SimpleStringSerializer<Serialized : Any> : StringSerializer<Serialized> {
  override fun serialize(value: Serialized): String = value.toString()
}

fun interface SimpleNullableStringSerializer<Serialized : Any> : NullableStringSerializer<Serialized> {
  override fun serialize(value: Serialized?): String? = value?.toString()
}

package com.aseemsavio.plugins

data class NativeImageArgument(val value: String) {
    init {
        value.trim()
        value.toCharArray().forEach { check(!it.isWhitespace()) { "Individual arguments should not have whitespace within." } }
    }
}

sealed class StoredConfigKey
object ArgumentsKey: StoredConfigKey()
object JarNameKey: StoredConfigKey()

sealed class StoredConfigValue
data class Arguments(val arguments: Set<NativeImageArgument>): StoredConfigValue()
data class JarName(val value: String): StoredConfigValue() {
    init {
        value.trim()
        value.toCharArray().forEach { check(!it.isWhitespace()) }
        check(value.endsWith(".jar")) { "You can only upload JAR files." }
    }
}

fun configMap(): ConfigMap = mutableMapOf()
typealias ConfigMap = MutableMap<StoredConfigKey, StoredConfigValue>

fun Set<NativeImageArgument>.toArguments() = Arguments(this)


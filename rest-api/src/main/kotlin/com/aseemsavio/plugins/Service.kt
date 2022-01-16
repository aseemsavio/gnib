package com.aseemsavio.plugins

fun arguments(argText: String) = argText.split("\\s".toRegex()).map { NativeImageArgument(it) }.toSet()

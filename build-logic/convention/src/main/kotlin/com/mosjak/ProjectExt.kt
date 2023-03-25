package com.mosjak

import org.gradle.api.plugins.ExtensionContainer

inline fun <reified T> ExtensionContainer.configure(noinline action: T.() -> Unit) {
    configure(T::class.java) { action(it) }
}

inline fun <reified T> ExtensionContainer.getByType(): T =
    getByType(T::class.java)

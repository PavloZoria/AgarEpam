@file:OptIn(ExperimentalTime::class)

package ua.com.epam.agar.hackathon.core

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun Any.printLine(message: String) {
    println("${this::class.simpleName} -> $message")
}

inline fun <T> Any.logTime(methodName: String, block: (() -> T)): T {
    var value: T?
    measureTime {
        value = block()
    }.run {
        this@logTime.printLine("$methodName duration: ${this.inWholeMilliseconds}")
    }
    return value!!
}

inline fun Any.logTime(methodName: String, block: (() -> Unit)) {
    measureTime {
        block()
    }.run {
        this@logTime.printLine("$methodName duration: ${this.inWholeMilliseconds}")
    }
}
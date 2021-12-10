package com.ua.epam.agar.io.hackathon.core

fun Any.printLine(message: String) {
    println("${this::class.simpleName} -> $message")
}
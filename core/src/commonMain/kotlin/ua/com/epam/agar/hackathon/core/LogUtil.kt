package ua.com.epam.agar.hackathon.core

fun Any.printLine(message: String) {
    println("${this::class.simpleName} -> $message")
}
package com.ua.agar.io.hackathon.api.game.storage

interface Storage<T : Any> {
    suspend fun set(value: T)
    suspend fun get(): T?
}
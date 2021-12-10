package com.ua.agar.io.hackathon.api.game.storage.local

import com.ua.agar.io.hackathon.api.game.storage.Storage

/**
 * Stores data in local RAM memory
 */
abstract class LocalStorage<T : Any> : Storage<T> {
    protected var field: T? = null

    override suspend fun get(): T {
        return if (exist()) {
            field!!
        } else {
            throw NoDataExistException()
        }
    }

    override suspend fun exist(): Boolean = field != null
}
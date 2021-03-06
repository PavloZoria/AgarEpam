package ua.com.epam.agar.hackathon.api.game.storage.local

import ua.com.epam.agar.hackathon.api.game.storage.Storage

/**
 * Stores data in local RAM memory
 */
internal abstract class LocalStorage<T : Any> : Storage<T> {
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
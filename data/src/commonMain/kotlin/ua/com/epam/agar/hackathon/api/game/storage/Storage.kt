package ua.com.epam.agar.hackathon.api.game.storage

/**
 * Data storage if the specific type
 */
interface Storage<T : Any> {
    /**
     * Set value of the type <T>
     */
    suspend fun set(value: T)

    /**
     * Return value of the type <T>
     */
    suspend fun get(): T

    /**
     * Check if data exist in storage
     */
    suspend fun exist(): Boolean

    /**
     * Clears data in the storage
     */
    fun invalidate()
}
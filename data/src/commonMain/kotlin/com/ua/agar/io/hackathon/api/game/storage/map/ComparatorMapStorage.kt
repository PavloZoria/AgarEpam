package com.ua.agar.io.hackathon.api.game.storage.map

import com.ua.agar.io.hackathon.api.game.storage.Storage
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState

class ComparatorMapStorage(private val storage: Storage<MapState>): Storage<MapState> {
    //TODO need to check here if the data is exist and change only fresh data
    override suspend fun set(value: MapState) {
        val mapState = storage.get()
    }

    override suspend fun get(): MapState? = storage.get()
}
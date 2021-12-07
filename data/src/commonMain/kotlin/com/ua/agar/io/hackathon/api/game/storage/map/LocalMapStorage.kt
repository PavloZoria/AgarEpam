package com.ua.agar.io.hackathon.api.game.storage.map

import com.ua.agar.io.hackathon.api.game.storage.Storage
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState

class LocalMapStorage: Storage<MapState> {
    private var mapState: MapState? = null

    override suspend fun set(value: MapState) {
        mapState = value
    }

    override suspend fun get(): MapState? = mapState
}
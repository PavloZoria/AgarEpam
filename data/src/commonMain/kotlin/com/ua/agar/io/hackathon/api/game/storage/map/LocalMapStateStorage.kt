package com.ua.agar.io.hackathon.api.game.storage.map

import com.ua.agar.io.hackathon.api.game.socket.model.data.MapStateModel
import com.ua.agar.io.hackathon.api.game.storage.local.LocalStorage

class LocalMapStateStorage : LocalStorage<MapStateModel>() {

    override suspend fun set(value: MapStateModel) {
        field = value
    }

    override fun invalidate() {
        field = null
    }
}
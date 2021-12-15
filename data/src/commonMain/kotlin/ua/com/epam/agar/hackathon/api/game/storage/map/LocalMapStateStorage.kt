package ua.com.epam.agar.hackathon.api.game.storage.map

import ua.com.epam.agar.hackathon.api.game.socket.model.data.MapStateModel
import ua.com.epam.agar.hackathon.api.game.storage.local.LocalStorage

internal class LocalMapStateStorage : LocalStorage<MapStateModel>() {

    override suspend fun set(value: MapStateModel) {
        field = value
    }

    override fun invalidate() {
        field = null
    }
}
package ua.com.epam.agar.hackathon.api.game.storage.map

import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModel
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.api.game.storage.local.LocalStorage

internal class LocalMapStateStorage : LocalStorage<LocalMapStateModel>() {

    override suspend fun set(value: LocalMapStateModel) {
        field = value
    }

    override fun invalidate() {
        field = null
    }
}
package ua.com.epam.agar.hackathon.api.game.storage.map

import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.api.game.storage.Storage
import ua.com.epam.agar.hackathon.core.logTime

internal class TimeLoggingMapStorage(private val storage: Storage<MapStateModel>) : Storage<NewStateMapStateModel> {

    override suspend fun set(newState: NewStateMapStateModel) {
        logTime("Set") {
            storage.set(newState)
        }
    }

    override suspend fun get(): NewStateMapStateModel {
        return logTime<NewStateMapStateModel>("Get") {
            storage.get()
        }
    }

    override suspend fun exist(): Boolean = logTime<Boolean>("exist") {
        storage.exist()
    }

    override fun invalidate() = logTime("invalidate") {
        storage.invalidate()
    }
}
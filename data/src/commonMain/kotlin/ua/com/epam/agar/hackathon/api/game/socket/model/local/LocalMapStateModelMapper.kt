package ua.com.epam.agar.hackathon.api.game.socket.model.local

import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper

internal class LocalMapStateModelMapper : Mapper<LocalMapStateModel, MapStateModel> {
    override fun mapFrom(item: MapStateModel): LocalMapStateModel = with(item) {
        return LocalMapStateModel(
            tickNumber = tickNumber,
            lastReceivedTick = lastReceivedTick,
            cellsOnMap = cellsOnMap?.associate { it.cellId to it }?.toMutableMap(),
            food = food?.associate { it.id to it }?.toMutableMap()
        )
    }

    override fun mapTo(item: LocalMapStateModel): MapStateModel = with(item) {
        return MapStateModel(
            tickNumber = tickNumber,
            lastReceivedTick = lastReceivedTick,
            cellsOnMap = cellsOnMap?.values,
            food = food?.values
        )
    }
}
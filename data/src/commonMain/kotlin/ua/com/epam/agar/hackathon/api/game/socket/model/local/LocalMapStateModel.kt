package ua.com.epam.agar.hackathon.api.game.socket.model.local

import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.cell.CellModel

typealias Id = String

internal data class LocalMapStateModel(
    val tickNumber: Int,
    val lastReceivedTick: Int? = null,
    val cellsOnMap: MutableMap<Id, CellModel>? = null,
    val food: MutableMap<Id, FoodModel>? = null,
)
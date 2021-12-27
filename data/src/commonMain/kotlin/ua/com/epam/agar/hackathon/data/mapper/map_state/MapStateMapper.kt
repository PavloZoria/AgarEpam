package ua.com.epam.agar.hackathon.data.mapper.map_state

import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.core.entity.cell.AlienCell
import ua.com.epam.agar.hackathon.core.entity.cell.MyCell
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.core.entity.mapper.mapListFrom
import ua.com.epam.agar.hackathon.core.entity.mapper.mapListTo
import ua.com.epam.agar.hackathon.data.mapper.map_state.cell.CellMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.food.FoodMapper

internal class MapStateMapper (
    private val cellMapper: CellMapper,
    private val foodMapper: FoodMapper,
) : Mapper<MapState, MapStateModel> {
    override fun mapFrom(item: MapStateModel): MapState = with(item) {
        val mapFrom = cellsOnMap?.mapListFrom(cellMapper) ?: emptyList()

        val myCells = mapFrom.filterIsInstance<MyCell>()
        val alienCells = mapFrom.filterIsInstance<AlienCell>()
        val foodList = food?.mapListFrom(foodMapper) ?: emptyList()
        MapState(gameTick = tickNumber, myCells, alienCells, foodList)
    }

    override fun mapTo(item: MapState): MapStateModel = with(item) {
        val my = myCells.mapListTo(cellMapper)
        val alien = alienCells.mapListTo(cellMapper)

        val foodList = food.mapListTo(foodMapper)
        MapStateModel(tickNumber = gameTick, cellsOnMap = HashSet(my + alien), food = HashSet(foodList))
    }
}
package com.ua.agar.io.hackathon.data.mapper.map_state

import com.ua.agar.io.hackathon.api.game.socket.model.data.MapStateModel
import com.ua.agar.io.hackathon.data.mapper.map_state.cell.CellMapper
import com.ua.agar.io.hackathon.data.mapper.map_state.food.FoodMapper
import com.ua.epam.agar.io.hackathon.core.entity.cell.AlienCell
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper
import com.ua.epam.agar.io.hackathon.core.entity.mapper.mapListFrom
import com.ua.epam.agar.io.hackathon.core.entity.mapper.mapListTo

internal class MapStateMapper(
    private val cellMapper: CellMapper = CellMapper(),
    private val foodMapper: FoodMapper = FoodMapper(),
) :
    Mapper<MapState, MapStateModel> {
    override fun mapFrom(item: MapStateModel): MapState = with(item) {
        val mapFrom = cellsOnMap.mapListFrom(cellMapper)

        val myCells = mapFrom.filterIsInstance<MyCell>()
        val alienCells = mapFrom.filterIsInstance<AlienCell>()
        val foodList = food.mapListFrom(foodMapper)
        MapState(gameTick = tickNumber, myCells, alienCells, foodList)
    }

    override fun mapTo(item: MapState): MapStateModel = with(item) {
        val my = myCells.mapListTo(cellMapper)
        val alien = alienCells.mapListTo(cellMapper)

        val foodList = food.mapListTo(foodMapper)
        MapStateModel(tickNumber = gameTick, ArrayList(my + alien), ArrayList(foodList))
    }
}
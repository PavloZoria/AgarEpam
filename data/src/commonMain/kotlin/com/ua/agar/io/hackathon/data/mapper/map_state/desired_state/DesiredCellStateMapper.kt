package com.ua.agar.io.hackathon.data.mapper.map_state.desired_state

import com.ua.agar.io.hackathon.api.game.socket.model.data.DesiredCellsStateModel
import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper
import com.ua.epam.agar.io.hackathon.core.entity.mapper.mapListFrom
import com.ua.epam.agar.io.hackathon.core.entity.mapper.mapListTo

class DesiredCellStateMapper(private val mapper: CellActivityMapper = CellActivityMapper()) :
    Mapper<DesiredCellsState, DesiredCellsStateModel> {
    override fun mapFrom(item: DesiredCellsStateModel): DesiredCellsState = with(item) {
        // DesiredCellsState(myCells.mapListFrom(mapper))
        TODO("No need to have this mapping")
    }

    override fun mapTo(item: DesiredCellsState): DesiredCellsStateModel = with(item) {
        DesiredCellsStateModel(myCells.mapListTo(mapper))
    }
}
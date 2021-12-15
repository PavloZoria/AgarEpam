package ua.com.epam.agar.hackathon.data.mapper.map_state.desired_state

import ua.com.epam.agar.hackathon.api.game.socket.model.data.DesiredCellsStateModel
import ua.com.epam.agar.hackathon.core.entity.main.DesiredCellsState
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.core.entity.mapper.mapListTo

internal class DesiredCellStateMapper(private val mapper: CellActivityMapper = CellActivityMapper()) :
    Mapper<DesiredCellsState, DesiredCellsStateModel> {
    override fun mapFrom(item: DesiredCellsStateModel): DesiredCellsState = with(item) {
        // DesiredCellsState(myCells.mapListFrom(mapper))
        TODO("No need to have this mapping")
    }

    override fun mapTo(item: DesiredCellsState): DesiredCellsStateModel = with(item) {
        DesiredCellsStateModel(myCells.mapListTo(mapper))
    }
}
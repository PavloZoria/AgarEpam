package ua.com.epam.agar.hackathon.data.mapper.map_state.desired_state

import ua.com.epam.agar.hackathon.core.entity.cell.property.TurnAction
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.data.cell.property.MoveActionModel

internal class MoveActionMapper : Mapper<TurnAction?, MoveActionModel?> {
    override fun mapFrom(item: MoveActionModel?): TurnAction? {
        TODO("Not needed to be implemented")
    }

    override fun mapTo(item: TurnAction?): MoveActionModel? = when (item) {
        TurnAction.Split -> MoveActionModel(split = true)
        is TurnAction.Merge -> MoveActionModel(merge = item.cellId.id)
        null -> null
    }
}
package ua.com.epam.agar.hackathon.data.mapper.map_state.desired_state

import ua.com.epam.agar.hackathon.core.entity.cell.property.MoveAction
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.data.cell.property.MoveActionModel

internal class MoveActionMapper : Mapper<MoveAction?, MoveActionModel?> {
    override fun mapFrom(item: MoveActionModel?): MoveAction? {
        TODO("Not needed to be implemented")
    }

    override fun mapTo(item: MoveAction?): MoveActionModel? = when (item) {
        MoveAction.Split -> MoveActionModel(split = true)
        is MoveAction.Merge -> MoveActionModel(merge = item.cellId.id)
        null -> null
    }
}
package ua.com.epam.agar.hackathon.data.mapper.map_state.cell

import ua.com.epam.agar.hackathon.core.entity.cell.property.Position
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel

internal class PositionMapper : Mapper<Position, PositionModel?> {
    override fun mapFrom(item: PositionModel?): Position = Position(item?.x ?: 0f, item?.y ?: 0f)

    override fun mapTo(item: Position): PositionModel = PositionModel(x = item.x, y = item.y)
}
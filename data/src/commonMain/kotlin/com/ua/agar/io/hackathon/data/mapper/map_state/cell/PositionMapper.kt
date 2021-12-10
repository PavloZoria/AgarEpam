package com.ua.agar.io.hackathon.data.mapper.map_state.cell

import com.ua.agar.io.hackathon.data.PositionModel
import com.ua.epam.agar.io.hackathon.core.entity.Position
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper

class PositionMapper : Mapper<Position, PositionModel?> {
    override fun mapFrom(item: PositionModel?): Position = Position(item?.x ?: 0f, item?.y ?: 0f)

    override fun mapTo(item: Position): PositionModel = PositionModel(x = item.x, y = item.y)
}
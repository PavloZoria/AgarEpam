package com.ua.agar.io.hackathon.data.mapper.map_state.cell

import com.ua.agar.io.hackathon.data.cell.CellModel
import com.ua.epam.agar.io.hackathon.core.entity.CellId
import com.ua.epam.agar.io.hackathon.core.entity.CellProperty
import com.ua.epam.agar.io.hackathon.core.entity.cell.AlienCell
import com.ua.epam.agar.io.hackathon.core.entity.cell.Cell
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper

internal class AlienCellMapper(
    private val positionMapper: PositionMapper = PositionMapper(),
    private val velocityMapper: VelocityMapper = VelocityMapper(),
) : Mapper<AlienCell, CellModel> {
    override fun mapFrom(item: CellModel): AlienCell = with(item) {
        AlienCell(
            cellId = CellId(cellId ?: ""),
            property = CellProperty(
                mass = mass ?: 0f,
                radius = radius ?: 0f,
                speed = speed ?: 0f,
                position = positionMapper.mapFrom(position),
                velocity = velocityMapper.mapFrom(velocity)!!,
                eatEfficiency = eatEfficiency ?: 0f
            )
        )
    }

    override fun mapTo(item: AlienCell): CellModel = with(item) {
        CellModel(
            cellId = cellId.id,
            mass = property.mass,
            radius = property.radius,
            speed = property.speed,
            position = positionMapper.mapTo(property.position),
            velocity = velocityMapper.mapTo(property.velocity),
            eatEfficiency = property.eatEfficiency,
        )
    }
}
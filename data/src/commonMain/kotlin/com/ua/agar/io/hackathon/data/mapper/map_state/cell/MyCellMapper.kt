package com.ua.agar.io.hackathon.data.mapper.map_state.cell

import com.ua.agar.io.hackathon.data.cell.CellModel
import com.ua.epam.agar.io.hackathon.core.entity.CellId
import com.ua.epam.agar.io.hackathon.core.entity.CellProperty
import com.ua.epam.agar.io.hackathon.core.entity.cell.Cell
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper

internal class MyCellMapper(
    private val positionMapper: PositionMapper = PositionMapper(),
    private val velocityMapper: VelocityMapper = VelocityMapper()
) : Mapper<MyCell, CellModel> {
    override fun mapFrom(item: CellModel): MyCell = with(item) {
        MyCell(
            cellId = CellId(cellId ?: ""),
            property = CellProperty(
                mass = mass ?: 0f,
                radius = radius ?: 0f,
                speed = speed ?: 0f,
                position = positionMapper.mapFrom(position),
                velocity = velocityMapper.mapFrom(velocity)!!,
                eatEfficiency = eatEfficiency ?: 0f
            ),
            availableEnergy = availableEnergy ?: 0f,
            canSplit = canSplit ?: false,
            canMerge = canMerge ?: false
        )
    }

    override fun mapTo(item: MyCell): CellModel = with(item) {
        CellModel(
            cellId = cellId.id,
            mass = property.mass,
            radius = property.radius,
            speed = property.speed,
            position = positionMapper.mapTo(property.position),
            velocity = velocityMapper.mapTo(property.velocity),
            eatEfficiency = property.eatEfficiency,
            availableEnergy = availableEnergy,
            canSplit = canSplit,
            canMerge = canMerge
        )
    }
}
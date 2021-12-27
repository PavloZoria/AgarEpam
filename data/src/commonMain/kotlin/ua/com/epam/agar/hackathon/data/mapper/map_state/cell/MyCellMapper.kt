package ua.com.epam.agar.hackathon.data.mapper.map_state.cell

import ua.com.epam.agar.hackathon.core.entity.cell.MyCell
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellId
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellProperty
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.data.cell.CellModel

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
                eatEfficiency = eatEfficiency ?: 0f,
                power = power ?: 0f
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
            power = property.power,
            position = positionMapper.mapTo(property.position),
            velocity = velocityMapper.mapTo(property.velocity),
            eatEfficiency = property.eatEfficiency,
            availableEnergy = availableEnergy,
            canSplit = canSplit,
            canMerge = canMerge,
            player = null,
            own = true,
            maxSpeed = null,
            deleted = null
        )
    }
}
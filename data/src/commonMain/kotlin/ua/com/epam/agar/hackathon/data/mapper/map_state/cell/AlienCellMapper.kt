package ua.com.epam.agar.hackathon.data.mapper.map_state.cell

import ua.com.epam.agar.hackathon.core.entity.cell.AlienCell
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellId
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellProperty
import ua.com.epam.agar.hackathon.core.entity.cell.property.Velocity
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.data.cell.CellModel

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
                velocity = velocityMapper.mapFrom(velocity) ?: Velocity.Default,
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
            player = null,
            own = false,
            availableEnergy = null,
            canSplit = null,
            canMerge = null,
            maxSpeed = null,
            deleted = null
        )
    }
}
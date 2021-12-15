package ua.com.epam.agar.hackathon.data.mapper.map_state.desired_state

import ua.com.epam.agar.hackathon.core.entity.cell.property.CellActivity
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.data.cell.property.CellActivityModel
import ua.com.epam.agar.hackathon.data.cell.property.GrowIntentionModel
import ua.com.epam.agar.hackathon.data.mapper.map_state.cell.VelocityMapper

internal class CellActivityMapper(private val velocityMapper: VelocityMapper = VelocityMapper()) :
    Mapper<CellActivity, CellActivityModel> {
    override fun mapFrom(item: CellActivityModel): CellActivity = with(item) {
        // CellActivity(CellId(cellId.id), Velocity(velocity?.x ?: 0, velocity?.y ?: 0))
        TODO("No need to have this mapping")
    }

    override fun mapTo(item: CellActivity): CellActivityModel = with(item) {
        CellActivityModel(
            cellId = cellId.id,
            cellSpeed = speed,
            velocity = velocityMapper.mapTo(item.velocity),
            growIntention = growIntention?.let {
                GrowIntentionModel(
                    eatEfficiency = growIntention?.eatEfficiency,
                    maxSpeed = growIntention?.maxSpeed,
                    power = growIntention?.power,
                    mass = growIntention?.mass)
            },
            additionalAction = null//TODO
        )
    }
}
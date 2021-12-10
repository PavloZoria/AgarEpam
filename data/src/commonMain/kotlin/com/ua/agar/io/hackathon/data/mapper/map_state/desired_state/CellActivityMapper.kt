package com.ua.agar.io.hackathon.data.mapper.map_state.desired_state

import com.ua.agar.io.hackathon.data.CellActivityModel
import com.ua.agar.io.hackathon.data.GrowIntentionModel
import com.ua.agar.io.hackathon.data.mapper.map_state.cell.VelocityMapper
import com.ua.epam.agar.io.hackathon.core.entity.CellActivity
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper

class CellActivityMapper(private val velocityMapper: VelocityMapper = VelocityMapper()) :
    Mapper<CellActivity, CellActivityModel> {
    override fun mapFrom(item: CellActivityModel): CellActivity = with(item) {
        // CellActivity(CellId(cellId.id), Velocity(velocity?.x ?: 0, velocity?.y ?: 0))
        TODO("Not yet implemented")
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
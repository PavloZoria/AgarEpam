package com.ua.agar.io.hackathon.data.mapper.map_state.cell

import com.ua.agar.io.hackathon.data.VelocityModel
import com.ua.epam.agar.io.hackathon.core.entity.Velocity
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper

class VelocityMapper : Mapper<Velocity?, VelocityModel?> {
    override fun mapFrom(item: VelocityModel?): Velocity? = item?.let { Velocity(item.x, item.y) }

    override fun mapTo(item: Velocity?): VelocityModel? = item?.let { VelocityModel(x = item.x, y = item.y) }
}
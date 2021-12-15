package ua.com.epam.agar.hackathon.data.mapper.map_state.cell

import ua.com.epam.agar.hackathon.core.entity.cell.property.Velocity
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel

internal class VelocityMapper : Mapper<Velocity?, VelocityModel?> {
    override fun mapFrom(item: VelocityModel?): Velocity? = item?.let { Velocity(item.x, item.y) }

    override fun mapTo(item: Velocity?): VelocityModel? = item?.let { VelocityModel(x = item.x, y = item.y) }
}
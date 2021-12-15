package ua.com.epam.agar.hackathon.data.mapper.config

import ua.com.epam.agar.hackathon.data.config.CellConfigModel
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.core.game.config.CellConfig

internal class CellConfigMapper : Mapper<CellConfig, CellConfigModel> {
    override fun mapFrom(item: CellConfigModel): CellConfig = CellConfig(
        minMass = item.minMass,
        maxMass = item.maxMass,
        minSpeed = item.minSpeed,
        maxSpeed = item.maxSpeed,
        volatilization = item.volatilization
    )

    override fun mapTo(item: CellConfig): CellConfigModel = CellConfigModel(
        minMass = item.minMass,
        maxMass = item.maxMass,
        minSpeed = item.minSpeed,
        maxSpeed = item.maxSpeed,
        volatilization = item.volatilization
    )
}

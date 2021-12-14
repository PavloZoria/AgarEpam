package com.ua.agar.io.hackathon.data.mapper.config

import com.ua.agar.io.hackathon.data.config.CellConfigModel
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper
import com.ua.epam.agar.io.hackathon.core.game.config.CellConfig

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

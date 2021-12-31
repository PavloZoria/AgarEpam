package ua.com.epam.agar.hackathon.data.mapper.config

import ua.com.epam.agar.hackathon.data.config.CellConfigModel
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.core.game.config.CellConfig

internal class CellConfigMapper : Mapper<CellConfig, CellConfigModel> {
    override fun mapFrom(item: CellConfigModel): CellConfig = CellConfig(
        massToRadius = item.massToRadius,
        toEatDiff = item.toEatDiff,
        collisionOffset = item.collisionOffset,

        minEatEfficiency = item.minEatEfficiency,
        maxEatEfficiency = item.maxEatEfficiency,
        energyToEatEfficiency = item.energyToEatEfficiency,

        minMass = item.minMass,
        maxMass = item.maxMass,
        energyToMass = item.energyToMass,

        minSpeed = item.minSpeed,
        maxSpeed = item.maxSpeed,
        energyToMaxSpeed = item.energyToMaxSpeed,

        minPower = item.minPower,
        maxPower = item.maxPower,
        energyToPower = item.energyToPower,

        maxVolatilization = item.maxVolatilization,
        minVolatilization = item.minVolatilization,
        energyToVolatilization = item.energyToVolatilization
    )

    override fun mapTo(item: CellConfig): CellConfigModel = CellConfigModel(
        massToRadius = item.massToRadius,
        toEatDiff = item.toEatDiff,
        collisionOffset = item.collisionOffset,

        minEatEfficiency = item.minEatEfficiency,
        maxEatEfficiency = item.maxEatEfficiency,
        energyToEatEfficiency = item.energyToEatEfficiency,

        minMass = item.minMass,
        maxMass = item.maxMass,
        energyToMass = item.energyToMass,

        minSpeed = item.minSpeed,
        maxSpeed = item.maxSpeed,
        energyToMaxSpeed = item.energyToMaxSpeed,

        minPower = item.minPower,
        maxPower = item.maxPower,
        energyToPower = item.energyToPower,

        maxVolatilization = item.maxVolatilization,
        minVolatilization = item.minVolatilization,
        energyToVolatilization = item.energyToVolatilization
    )
}

package ua.com.epam.agar.hackathon.core.game.config

class CellConfig(
    val massToRadius: Float,
    val toEatDiff: Float,
    val collisionOffset: Float,

    val minEatEfficiency: Float,
    val maxEatEfficiency: Float,
    val energyToEatEfficiency: Float,

    val minMass: Float,
    val maxMass: Float,
    val energyToMass: Float,

    val minSpeed: Float,
    val maxSpeed: Float,
    val energyToMaxSpeed: Float,

    val minPower: Float,
    val maxPower: Float,
    val energyToPower: Float,

    val maxVolatilization: Float,
    val minVolatilization: Float,
    val energyToVolatilization: Float,
)

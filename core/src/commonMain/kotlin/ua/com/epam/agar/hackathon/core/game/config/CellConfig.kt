package ua.com.epam.agar.hackathon.core.game.config

class CellConfig(
    /**
     * radius = Sqrt(mass / PI) * massToRadius
     */
    val massToRadius: Float,
    /**
     * mass difference needed to eat another cell
     */
    val toEatDiff: Float,
    /**
     * cells must cross on this value to eat
     * (cell1.radius * collisionOffset + cell2.radius * collisionOffset) < distance
     */
    val collisionOffset: Float,

    val minEatEfficiency: Float,
    val maxEatEfficiency: Float,
    /**
     * energy exchange rate for eat efficiency
     */
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

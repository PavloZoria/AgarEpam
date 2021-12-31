package ua.com.epam.agar.hackathon.data.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CellConfigModel(
    @SerialName("massToRadius")
    val massToRadius: Float,
    @SerialName("toEatDiff")
    val toEatDiff: Float,
    @SerialName("collisionOffset")
    val collisionOffset: Float,

    @SerialName("minEatEfficiency")
    val minEatEfficiency: Float,
    @SerialName("maxEatEfficiency")
    val maxEatEfficiency: Float,
    @SerialName("energyToEatEfficiency")
    val energyToEatEfficiency: Float,

    @SerialName("minMass")
    val minMass: Float,
    @SerialName("maxMass")
    val maxMass: Float,
    @SerialName("energyToMass")
    val energyToMass: Float,

    @SerialName("minSpeed")
    val minSpeed: Float,
    @SerialName("maxSpeed")
    val maxSpeed: Float,
    @SerialName("energyToMaxSpeed")
    val energyToMaxSpeed: Float,

    @SerialName("minPower")
    val minPower: Float,
    @SerialName("maxPower")
    val maxPower: Float,
    @SerialName("energyToPower")
    val energyToPower: Float,

    @SerialName("maxVolatilization")
    val maxVolatilization: Float,
    @SerialName("minVolatilization")
    val minVolatilization: Float,
    @SerialName("energyToVolatilization")
    val energyToVolatilization: Float,
)

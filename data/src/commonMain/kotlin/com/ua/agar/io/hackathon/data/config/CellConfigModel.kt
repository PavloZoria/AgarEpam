package com.ua.agar.io.hackathon.data.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CellConfigModel(
    @SerialName("minMass")
    val minMass: Float,
    @SerialName("maxMass")
    val maxMass: Float,

    @SerialName("minSpeed")
    val minSpeed: Float,
    @SerialName("maxSpeed")
    val maxSpeed: Float,

    @SerialName("volatilization")
    val volatilization: Float
)

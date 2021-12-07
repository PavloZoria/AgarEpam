package com.ua.agar.io.hackathon.data

import kotlinx.serialization.Serializable

@Serializable
data class CellPropertyModel(
    val mass: Float,
    val radius: Float,
    val speed: Float,
    val position: PositionModel,
    val velocity: VelocityModel,
    val eatEfficiency: Float
)

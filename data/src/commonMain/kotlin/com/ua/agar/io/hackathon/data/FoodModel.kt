package com.ua.agar.io.hackathon.data

import kotlinx.serialization.Serializable

@Serializable
data class FoodModel(
    val mass: Float,
    val position: PositionModel,
)
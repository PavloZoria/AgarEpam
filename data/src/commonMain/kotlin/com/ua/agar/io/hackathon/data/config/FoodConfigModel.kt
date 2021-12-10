package com.ua.agar.io.hackathon.data.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodConfigModel(
    @SerialName("mass")
    val mass: Float
)

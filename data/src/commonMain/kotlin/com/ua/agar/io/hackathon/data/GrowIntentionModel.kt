package com.ua.agar.io.hackathon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GrowIntentionModel(
    val eatEfficiency: Float? = null,
    val maxSpeed: Float? = null,
    val power: Float? = null,
    val mass: Float? = null
)

package com.ua.agar.io.hackathon.data

import kotlinx.serialization.Serializable

@Serializable
internal data class GrowIntentionModel(
    val eatEfficiency: Float? = null,
    val maxSpeed: Float? = null,
    val power: Float? = null,
    val mass: Float? = null
)

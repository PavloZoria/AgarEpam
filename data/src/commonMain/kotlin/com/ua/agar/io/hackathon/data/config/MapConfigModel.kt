package com.ua.agar.io.hackathon.data.config

import kotlinx.serialization.Serializable

@Serializable
data class MapConfigModel(
    val height: Float,
    val width: Float
)

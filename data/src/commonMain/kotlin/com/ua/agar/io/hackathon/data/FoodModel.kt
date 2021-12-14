package com.ua.agar.io.hackathon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class FoodModel(
    @SerialName("id")
    val id: String? = null,
    @SerialName("position")
    val position: PositionModel?,
    @SerialName("del")
    val deleted: Boolean? = null,
)
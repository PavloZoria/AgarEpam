package com.ua.agar.io.hackathon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CellActivityModel(
    @SerialName("id")
    val cellId: String,
    @SerialName("speed")
    val cellSpeed: Float = 0f,
    @SerialName("velocity")
    val velocity: VelocityModel? = null,
    @SerialName("growIntention")
    val growIntention: GrowIntentionModel? = null,
    @SerialName("additionalAction")
    val additionalAction: MoveActionModel? = null
)


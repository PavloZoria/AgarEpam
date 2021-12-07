package com.ua.agar.io.hackathon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveModel(
    val velocity: VelocityModel,
)//TODO додати логіку споживання речовин?

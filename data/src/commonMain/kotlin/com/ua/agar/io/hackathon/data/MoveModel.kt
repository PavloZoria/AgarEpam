package com.ua.agar.io.hackathon.data

import kotlinx.serialization.Serializable

@Serializable
internal data class MoveModel(
    val velocity: VelocityModel,
)//TODO додати логіку споживання речовин?

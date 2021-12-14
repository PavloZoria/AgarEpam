package com.ua.agar.io.hackathon.data.cell

import com.ua.agar.io.hackathon.data.PositionModel
import com.ua.agar.io.hackathon.data.VelocityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CellModel(
    @SerialName("id")
    val cellId: String? = null,
    @SerialName("player")
    val player: String? = null,
    @SerialName("own")
    val own: Boolean? = null,

    @SerialName("mass")
    val mass: Float? = null,
    @SerialName("radius")
    val radius: Float? = null,
    @SerialName("position")
    val position: PositionModel? = null,
    @SerialName("velocity")
    val velocity: VelocityModel? = null,
    @SerialName("availableEnergy")
    val availableEnergy: Float? = null,
    @SerialName("canSplit")
    val canSplit: Boolean? = null,
    @SerialName("canMerge")
    val canMerge: Boolean? = null,
    @SerialName("speed")
    val speed: Float? = null,
    @SerialName("eatEfficiency")
    val eatEfficiency: Float? = null,

    @SerialName("del")
    val deleted: Boolean? = null
)
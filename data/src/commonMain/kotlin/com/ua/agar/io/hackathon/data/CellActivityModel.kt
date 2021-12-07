package com.ua.agar.io.hackathon.data

import kotlinx.serialization.Serializable

@Serializable
data class CellActivityModel(
    val cellId: CellIdModel,
    val velocity: VelocityModel? = null,
    val growIntention: GrowIntentionModel? = null,
    val additionalAction: MoveActionModel? = null
) {
    constructor(
        cellId: CellIdModel,
        velocity: VelocityModel
    ) : this(cellId, velocity, null, null)
}


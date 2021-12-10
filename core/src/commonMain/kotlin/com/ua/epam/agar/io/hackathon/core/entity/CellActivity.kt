package com.ua.epam.agar.io.hackathon.core.entity

data class CellActivity(
    val cellId: CellId,
    val speed: Float = 0.0f,
    val velocity: Velocity? = null,
    val growIntention: GrowIntention? = null,
    val additionalAction: MoveAction? = null,
) {
    constructor(
        cellId: CellId,
        velocity: Velocity,
    ) : this(cellId, velocity = velocity, growIntention = null, additionalAction = null)
}


package com.ua.epam.agar.io.hackathon.core.entity

data class CellActivity(
    val cellId: CellId,
    val velocity: Velocity? = null,
    val growIntention: GrowIntention? = null,
    val additionalAction: MoveAction? = null
) {
    constructor(
        cellId: CellId,
        velocity: Velocity
    ) : this(cellId, velocity, null, null)
}


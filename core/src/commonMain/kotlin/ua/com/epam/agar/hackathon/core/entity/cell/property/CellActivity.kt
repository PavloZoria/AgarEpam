package ua.com.epam.agar.hackathon.core.entity.cell.property

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

    init {
        require(speed in 0.0..1.0) {
            "Speed should be in range 0.0..1.0"
        }
    }
}


package ua.com.epam.agar.hackathon.core.entity.cell.property

/**
 * This item represents the way how we want to update our item based on [cellId]
 */
data class CellActivity(
    val cellId: CellId,
    /**
     * Max value - 1.0. It means that our required speed is 100% of maximum possible speed
     * Min value - 0.0. It means that our required speed is 0% of maximum possible speed
     */
    val speed: Float = 0.0f,

    /**
     * Speed vector. The direction where we want to move our cell.
     * Vector length will be ignored and will be calculated based on your speed
     */
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


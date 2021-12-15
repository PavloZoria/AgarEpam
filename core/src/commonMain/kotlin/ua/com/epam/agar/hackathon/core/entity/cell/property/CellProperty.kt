package ua.com.epam.agar.hackathon.core.entity.cell.property

data class CellProperty(
    val mass: Float,
    val radius: Float,
    val speed: Float,
    val position: Position,
    val velocity: Velocity,
    val eatEfficiency: Float
)

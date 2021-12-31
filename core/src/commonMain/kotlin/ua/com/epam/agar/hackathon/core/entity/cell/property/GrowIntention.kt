package ua.com.epam.agar.hackathon.core.entity.cell.property

/**
 * Represent in which way we want to update our cell parameters
 */
data class GrowIntention(
    val eatEfficiency: Float? = null,
    val maxSpeed: Float? = null,
    val power: Float? = null,
    val mass: Float? = null,
    val volatilization: Float? = null
)

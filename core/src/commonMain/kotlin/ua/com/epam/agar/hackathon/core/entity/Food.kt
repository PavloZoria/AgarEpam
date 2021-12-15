package ua.com.epam.agar.hackathon.core.entity

import ua.com.epam.agar.hackathon.core.entity.cell.property.Position

data class Food(
    val id: String,
    val mass: Float,
    val position: Position,
)
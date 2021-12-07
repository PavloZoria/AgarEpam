package com.ua.epam.agar.io.hackathon.core.entity

data class CellProperty(
    val mass: Float,
    val radius: Float,
    val speed: Float,
    val position: Position,
    val velocity: Velocity,
    val eatEfficiency: Float
)

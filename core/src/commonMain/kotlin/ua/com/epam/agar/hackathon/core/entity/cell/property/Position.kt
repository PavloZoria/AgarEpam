package ua.com.epam.agar.hackathon.core.entity.cell.property

import kotlin.math.sqrt

data class Position(val x: Float, val y: Float)

fun Position.distanceTo(target: Position?): Double = target?.let { distanceBetweenPoints(this, target) } ?: 0.0

fun Position.distanceToPosition(target: Position): Double = distanceBetweenPoints(this, target)

fun distanceBetweenPoints(
    position1: Position,
    position2: Position,
): Double {
    val (x1, y1) = position1
    val (x2, y2) = position2
    return distanceBetweenPoints(x1, y1, x2, y2)
}

fun distanceBetweenPoints(
    x1: Float,
    y1: Float,
    x2: Float,
    y2: Float,
) = sqrt(((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)).toDouble())
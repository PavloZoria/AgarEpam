package ua.com.epam.agar.hackathon.core.entity.cell.property

/**
 * Speed vector. The direction where we want to move our cell.
 * Vector length will be ignored and will be calculated based on your speed
 */
data class Velocity(val x: Float?, val y: Float?) {
    companion object {
        /**
         * Velocity that displays that we want to stay in one place
         */
        val Default = Velocity(0f, 0f)
    }
}

fun Position.moveTo(target: Position?): Velocity = target?.let {
    moveToPosition(target)
} ?: Velocity.Default

fun Position.moveToPosition(target: Position): Velocity {
    val (fromX, fromY) = this
    val (targetX, targetY) = target
    return Velocity(targetX - fromX, targetY - fromY)
}

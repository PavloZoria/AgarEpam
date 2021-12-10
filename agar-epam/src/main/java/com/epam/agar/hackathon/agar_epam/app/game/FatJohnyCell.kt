package com.epam.agar.hackathon.agar_epam.app.game

import com.ua.epam.agar.io.hackathon.core.entity.CellActivity
import com.ua.epam.agar.io.hackathon.core.entity.Food
import com.ua.epam.agar.io.hackathon.core.entity.Velocity
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.distanceTo
import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic
import com.ua.epam.agar.io.hackathon.core.printLine

/**
 * Test cell that search for the closest food and eat it
 */
class FatJohnyCell : CellLogic() {
    override fun handleGameUpdate(mapState: MapState): DesiredCellsState? {
        printLine("TestCell mapState.size: ${mapState.myCells}")
        return mapState.myCells.map { myCell ->
            val (cellX, cellY) = myCell.property.position
            val (foodX, foodY) = findClosestFood(mapState, myCell).position


            CellActivity(myCell.cellId, speed = 1.0f, velocity = Velocity(foodX - cellX, foodY - cellY))
        }.run {
            printLine("TestCell: ${this.size}")
            DesiredCellsState(this)
        }
        // return null
    }

    private fun findClosestFood(
        mapState: MapState,
        myCell: MyCell,
    ): Food {
        val distanceWithFood = mapState.food.map { food ->
            val distance = food.position.distanceTo(myCell.property.position)
            printLine("findClosestFood: $distance")
            distance to food
        }

        var closestFood: Pair<Double, Food>? = null
        distanceWithFood.forEach {
            if (closestFood == null) {
                closestFood = it
            } else {
                if (it.first < (closestFood?.first ?: 0.0)) {
                    closestFood = it
                }
            }
        }

        printLine("closestFood: $closestFood")
        return closestFood!!.second
    }
}
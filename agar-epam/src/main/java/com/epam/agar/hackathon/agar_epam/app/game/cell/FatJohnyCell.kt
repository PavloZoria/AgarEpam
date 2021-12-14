package com.epam.agar.hackathon.agar_epam.app.game.cell

import com.ua.epam.agar.io.hackathon.core.entity.CellActivity
import com.ua.epam.agar.io.hackathon.core.entity.Food
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.distanceTo
import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.entity.moveTo
import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic

/**
 * Test cell that search for the closest food and eat it
 */
class FatJohnyCell : CellLogic() {
    override fun handleGameUpdate(mapState: MapState): DesiredCellsState? {
        return mapState.myCells.map { myCell ->
            val from = myCell.property.position
            val target = findClosestFood(mapState, myCell)?.position


            CellActivity(myCell.cellId, speed = 1.0f, velocity = from.moveTo(target))
        }.run {
            DesiredCellsState(this)
        }
        // return null
    }

    private fun findClosestFood(
        mapState: MapState,
        myCell: MyCell,
    ): Food? {
        val distanceWithFood = mapState.food.map { food ->
            val distance = food.position.distanceTo(myCell.property.position)
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

        return closestFood?.second
    }
}
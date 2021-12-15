package com.epam.agar.hackathon.agar_epam.app.game.cell

import ua.com.epam.agar.hackathon.core.entity.cell.Cell
import ua.com.epam.agar.hackathon.core.entity.cell.MyCell
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellActivity
import ua.com.epam.agar.hackathon.core.entity.cell.property.distanceTo
import ua.com.epam.agar.hackathon.core.entity.main.DesiredCellsState
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic
import ua.com.epam.agar.hackathon.core.printLine

/**
 * Test cell that search for the closest food and eat it
 */
class ChaserDanCell : CellLogic() {

    override fun handleGameUpdate(mapState: MapState): DesiredCellsState? {
        printLine("handleGameUpdate: myCells: ${mapState.myCells}, \t\nalienCells:${mapState.alienCells}")
        return mapState.myCells.map { myCell ->
            val targetCell = findClosestCell(mapState, myCell)

            printLine("targetCell: $targetCell")
            val distanceTo = myCell.distanceTo(targetCell)
            val radiusSum = myCell.property.radius + (targetCell?.property?.radius ?: 0f)
            val velocity = if (distanceTo == radiusSum.toDouble()) {
                // touchEachOther
                targetCell?.moveTo(myCell)
            } else if (distanceTo > radiusSum) {
                // not touch
                myCell.moveTo(targetCell)//move to the targetCell
            } else if (distanceTo < radiusSum) {
                // intersects
                targetCell?.moveTo(myCell)
            } else {
                targetCell?.moveTo(myCell)
            }

                CellActivity(myCell.cellId, speed = 1.0f, velocity = velocity)
        }.run {
            printLine("TestCell: ${this.size}")
            DesiredCellsState(this)
        }
        // return null
    }

    private fun findClosestCell(
        mapState: MapState,
        myCell: MyCell,
    ): Cell? {
        val distanceWithCell = mapState.alienCells.map { cell ->
            val distance = cell.property.position.distanceTo(myCell.property.position)
            printLine("findClosestCell: $distance")
            distance to cell
        }

        var closestFood: Pair<Double, Cell>? = null
        distanceWithCell.forEach {
            if (closestFood == null) {
                closestFood = it
            } else {
                if (it.first < (closestFood?.first ?: 0.0)) {
                    closestFood = it
                }
            }
        }

        printLine("closestFood: $closestFood")
        return closestFood?.second
    }
}
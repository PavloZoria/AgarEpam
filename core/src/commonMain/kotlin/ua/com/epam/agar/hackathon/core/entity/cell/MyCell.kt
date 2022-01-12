package ua.com.epam.agar.hackathon.core.entity.cell

import ua.com.epam.agar.hackathon.core.entity.cell.property.CellId
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellProperty

data class MyCell(
    override val cellId: CellId,
    override val property: CellProperty,

    /**
     * The amount of energy that you have to digest in order to grow and develop
     */
    val availableEnergy: Float,
    val canSplit: Boolean,
    val canMerge: Boolean,
    /**
     * Amount of time that left until this cell be able to merge again
     */
    val mergeTimer: Long
): Cell(cellId, property)
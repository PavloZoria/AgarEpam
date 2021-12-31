package ua.com.epam.agar.hackathon.core.entity.cell

import ua.com.epam.agar.hackathon.core.entity.cell.property.CellId
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellProperty

data class MyCell(
    override val cellId: CellId,
    override val property: CellProperty,

    val availableEnergy: Float,//ту яку може спожити
    val canSplit: Boolean,
    val canMerge: Boolean,
    val mergeTimer: Long
): Cell(cellId, property)
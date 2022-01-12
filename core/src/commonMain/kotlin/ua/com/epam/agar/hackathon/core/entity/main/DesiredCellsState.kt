package ua.com.epam.agar.hackathon.core.entity.main

import ua.com.epam.agar.hackathon.core.entity.cell.property.CellActivity

/**
 * Represents the state for each of your cells in which
 * you want to see your cells on the next game tick
 */
data class DesiredCellsState(
    val myCells: List<CellActivity>,
) {
    constructor(cell: CellActivity) : this(listOf(cell))
}
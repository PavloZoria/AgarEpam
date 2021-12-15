package ua.com.epam.agar.hackathon.core.entity.main

import ua.com.epam.agar.hackathon.core.entity.cell.property.CellActivity

/**
 * Request
 */
data class DesiredCellsState(
    val myCells: List<CellActivity>,
)
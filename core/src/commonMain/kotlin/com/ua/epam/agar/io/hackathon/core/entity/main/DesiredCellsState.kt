package com.ua.epam.agar.io.hackathon.core.entity.main

import com.ua.epam.agar.io.hackathon.core.entity.CellActivity

/**
 * Request
 */
data class DesiredCellsState(
    val myCells: List<CellActivity>,
)
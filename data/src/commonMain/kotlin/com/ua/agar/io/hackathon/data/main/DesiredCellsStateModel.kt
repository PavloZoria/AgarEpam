package com.ua.agar.io.hackathon.data.main

import com.ua.agar.io.hackathon.data.CellActivityModel

/**
 * Request
 */
data class DesiredCellsStateModel(
    val myCells: List<CellActivityModel>,
)
package com.ua.agar.io.hackathon.data.cell

import com.ua.agar.io.hackathon.data.CellIdModel
import com.ua.agar.io.hackathon.data.CellPropertyModel

data class MyCellModel(
    override val cellId: CellIdModel,
    override val property: CellPropertyModel,

    val availableEnergy: Float,//ту яку може спожити
    val canSplit: Boolean,
    val canMerge: Boolean
): Cell(cellId, property)
package com.ua.epam.agar.io.hackathon.core.entity.cell

import com.ua.epam.agar.io.hackathon.core.entity.CellId
import com.ua.epam.agar.io.hackathon.core.entity.CellProperty
import com.ua.epam.agar.io.hackathon.core.entity.cell.Cell

data class MyCell(
    override val cellId: CellId,
    override val property: CellProperty,

    val availableEnergy: Float,//ту яку може спожити
    val canSplit: Boolean,
    val canMerge: Boolean
): Cell(cellId, property)
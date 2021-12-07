package com.ua.agar.io.hackathon.data.cell

import com.ua.agar.io.hackathon.data.CellIdModel
import com.ua.agar.io.hackathon.data.CellPropertyModel

data class AlienCell(
    override val cellId: CellIdModel,
    override val property: CellPropertyModel
): Cell(cellId, property)
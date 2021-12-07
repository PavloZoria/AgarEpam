package com.ua.epam.agar.io.hackathon.core.entity.cell

import com.ua.epam.agar.io.hackathon.core.entity.CellId
import com.ua.epam.agar.io.hackathon.core.entity.CellProperty

data class AlienCell(
    override val cellId: CellId,
    override val property: CellProperty
): Cell(cellId, property)
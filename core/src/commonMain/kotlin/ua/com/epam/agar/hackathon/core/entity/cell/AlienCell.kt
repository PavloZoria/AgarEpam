package ua.com.epam.agar.hackathon.core.entity.cell

import ua.com.epam.agar.hackathon.core.entity.cell.property.CellId
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellProperty

data class AlienCell(
    override val cellId: CellId,
    override val property: CellProperty
): Cell(cellId, property)
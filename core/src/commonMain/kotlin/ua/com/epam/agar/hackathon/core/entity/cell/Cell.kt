package ua.com.epam.agar.hackathon.core.entity.cell

import ua.com.epam.agar.hackathon.core.entity.cell.property.CellId
import ua.com.epam.agar.hackathon.core.entity.cell.property.CellProperty
import ua.com.epam.agar.hackathon.core.entity.cell.property.distanceTo
import ua.com.epam.agar.hackathon.core.entity.cell.property.moveTo

abstract class Cell(
    open val cellId: CellId,
    open val property: CellProperty,
) {
    fun distanceTo(cell: Cell?) = property.position.distanceTo(cell?.property?.position)
    fun moveTo(cell: Cell?) = property.position.moveTo(cell?.property?.position)
}
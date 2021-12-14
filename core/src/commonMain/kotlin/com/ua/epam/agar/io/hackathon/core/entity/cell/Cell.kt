package com.ua.epam.agar.io.hackathon.core.entity.cell

import com.ua.epam.agar.io.hackathon.core.entity.CellId
import com.ua.epam.agar.io.hackathon.core.entity.CellProperty
import com.ua.epam.agar.io.hackathon.core.entity.distanceTo
import com.ua.epam.agar.io.hackathon.core.entity.moveTo

abstract class Cell(
    open val cellId: CellId,
    open val property: CellProperty,
) {
    fun distanceTo(cell: Cell?) = property.position.distanceTo(cell?.property?.position)
    fun moveTo(cell: Cell?) = property.position.moveTo(cell?.property?.position)
}
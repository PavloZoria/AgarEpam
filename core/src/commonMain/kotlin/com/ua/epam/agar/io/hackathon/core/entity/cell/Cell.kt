package com.ua.epam.agar.io.hackathon.core.entity.cell

import com.ua.epam.agar.io.hackathon.core.entity.CellId
import com.ua.epam.agar.io.hackathon.core.entity.CellProperty

abstract class Cell(
    open val cellId: CellId,
    open val property: CellProperty
)
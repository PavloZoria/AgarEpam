package com.ua.agar.io.hackathon.data.cell

import com.ua.agar.io.hackathon.data.CellIdModel
import com.ua.agar.io.hackathon.data.CellPropertyModel

abstract class Cell(
    open val cellId: CellIdModel,
    open val property: CellPropertyModel
)
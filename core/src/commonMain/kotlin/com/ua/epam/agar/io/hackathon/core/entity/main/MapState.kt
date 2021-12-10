package com.ua.epam.agar.io.hackathon.core.entity.main

import com.ua.epam.agar.io.hackathon.core.entity.Food
import com.ua.epam.agar.io.hackathon.core.entity.cell.AlienCell
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell

/**
 * Response
 */
data class MapState(
    val gameTick: Int,
    val myCells: List<MyCell>,
    val alienCells: List<AlienCell>,
    val food: List<Food>
)
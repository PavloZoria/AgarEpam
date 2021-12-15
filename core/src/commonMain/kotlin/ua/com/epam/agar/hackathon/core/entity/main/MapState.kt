package ua.com.epam.agar.hackathon.core.entity.main

import ua.com.epam.agar.hackathon.core.entity.Food
import ua.com.epam.agar.hackathon.core.entity.cell.AlienCell
import ua.com.epam.agar.hackathon.core.entity.cell.MyCell

/**
 * Response
 */
data class MapState(
    val gameTick: Int,
    val myCells: List<MyCell>,
    val alienCells: List<AlienCell>,
    val food: List<Food>
)
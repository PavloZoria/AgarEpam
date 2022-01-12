package ua.com.epam.agar.hackathon.core.entity.main

import ua.com.epam.agar.hackathon.core.entity.Food
import ua.com.epam.agar.hackathon.core.entity.cell.AlienCell
import ua.com.epam.agar.hackathon.core.entity.cell.MyCell

/**
 * Response
 */
data class MapState(
    /**
     * Current game tick
     */
    val gameTick: Int,
    /**
     * Cells that you can work with in terms of developing/moving/updating them
     */
    val myCells: List<MyCell>,
    /**
     * Alien cells that potentially can be harmful.
     * You have to be careful and avoid them until you are sure that you can eat it
     */
    val alienCells: List<AlienCell>,
    /**
     * All food that exists on map
     */
    val food: List<Food>
)
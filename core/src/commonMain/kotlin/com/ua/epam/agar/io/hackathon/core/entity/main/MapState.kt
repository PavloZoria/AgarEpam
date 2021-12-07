package com.ua.epam.agar.io.hackathon.core.entity.main

import com.ua.epam.agar.io.hackathon.core.entity.Food
import com.ua.epam.agar.io.hackathon.core.entity.cell.AlienCell

/**
 * Response
 */
data class MapState(
    val alienCells: List<AlienCell>,
    val food: List<Food>
)
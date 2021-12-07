package com.ua.agar.io.hackathon.data.main

import com.ua.agar.io.hackathon.data.FoodModel
import com.ua.epam.agar.io.hackathon.core.entity.cell.AlienCell

/**
 * Response
 */
data class MapStateModel(
    val alienCells: List<AlienCell>,
    val food: List<FoodModel>
)
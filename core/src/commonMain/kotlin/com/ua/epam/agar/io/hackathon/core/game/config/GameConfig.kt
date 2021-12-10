package com.ua.epam.agar.io.hackathon.core.game.config

data class GameConfig(
    val tickTime: Long,
    val cellConfig: CellConfig,
    val mapConfig: MapConfig,
    val foodConfig: FoodConfig
)

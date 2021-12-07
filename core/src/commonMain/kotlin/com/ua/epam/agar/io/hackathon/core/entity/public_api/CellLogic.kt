package com.ua.epam.agar.io.hackathon.core.entity.public_api

import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig

/**
 * Need to be implemented by the student
 */
abstract class CellLogic {

    private lateinit var gameConfig: GameConfig

    fun configure(gameConfig: GameConfig) {
        this.gameConfig = gameConfig
    }

    abstract fun handleGameUpdate(myCells: List<MyCell>, mapState: MapState): DesiredCellsState?
}
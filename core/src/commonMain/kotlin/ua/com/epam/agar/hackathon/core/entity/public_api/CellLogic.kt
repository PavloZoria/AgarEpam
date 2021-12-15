package ua.com.epam.agar.hackathon.core.entity.public_api

import ua.com.epam.agar.hackathon.core.entity.main.DesiredCellsState
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.game.config.GameConfig

/**
 * Need to be implemented by the student
 */
abstract class CellLogic {

    private lateinit var gameConfig: GameConfig

    fun configure(gameConfig: GameConfig) {
        this.gameConfig = gameConfig
    }

    abstract fun handleGameUpdate(mapState: MapState): DesiredCellsState?
}
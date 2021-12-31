package ua.com.epam.agar.engine

import ua.com.epam.agar.engine.provider.engine.DefaultEngineProvider
import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic
import ua.com.epam.agar.hackathon.core.game.engine.InternalGameEngine
import ua.com.epam.agar.hackathon.core.game.engine.GameEngine

object GameEngine : GameEngine {

    private val gameEngine: GameEngine = InternalGameEngine(DefaultEngineProvider())

    override fun initialize(cellLogic: CellLogic) {
        gameEngine.initialize(cellLogic = cellLogic)
    }

    override fun startGame(roomId: String, isTesting: Boolean) {
        gameEngine.startGame(roomId = roomId, isTesting = isTesting)
    }

    override fun stopGame() {
        gameEngine.stopGame()
    }
}
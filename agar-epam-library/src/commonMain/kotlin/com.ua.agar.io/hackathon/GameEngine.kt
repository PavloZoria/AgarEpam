package com.ua.agar.io.hackathon

import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic
import com.ua.epam.agar.io.hackathon.core.game.engine.GameEngine
import com.ua.epam.agar.io.hackathon.core.game.engine.InternalGameEngine

object GameEngine : GameEngine {

    private val gameEngine: GameEngine = InternalGameEngine(DefaultEngineProvider())

    override fun initialize(cellLogic: CellLogic) {
        gameEngine.initialize(cellLogic = cellLogic)
    }

    override fun startGame(roomId: String) {
        gameEngine.startGame(roomId = roomId)
    }

    override fun stopGame() {
        gameEngine.stopGame()
    }
}
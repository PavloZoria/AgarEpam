package ua.com.epam.agar.hackathon.core.game.engine

import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic

interface GameEngine {
    fun initialize(cellLogic: CellLogic)

    fun startGame(roomId: String, isTraining: Boolean)

    fun stopGame()
}
package com.ua.epam.agar.io.hackathon.core.game.engine

import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic

interface GameEngine {
    fun initialize(cellLogic: CellLogic)

    fun startGame(roomId: String)

    fun stopGame()
}
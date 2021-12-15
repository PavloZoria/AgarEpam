package ua.com.epam.agar.hackathon.core.game.engine.provider

import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic
import ua.com.epam.agar.hackathon.core.game.Engine

interface EngineProvider {
    fun provideEngine(cellLogic: CellLogic): Engine
}
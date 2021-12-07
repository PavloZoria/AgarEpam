package com.ua.epam.agar.io.hackathon.core.game.engine.provider

import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic
import com.ua.epam.agar.io.hackathon.core.game.Engine

interface EngineProvider {
    fun provideEngine(cellLogic: CellLogic): Engine
}
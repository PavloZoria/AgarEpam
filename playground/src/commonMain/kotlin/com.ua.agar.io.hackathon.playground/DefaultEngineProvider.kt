package com.ua.agar.io.hackathon.playground

import com.ua.agar.io.hackathon.engine.DefaultEngine
import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic
import com.ua.epam.agar.io.hackathon.core.game.Engine
import com.ua.epam.agar.io.hackathon.core.game.engine.provider.EngineProvider

class DefaultEngineProvider : EngineProvider {
    override fun provideEngine(cellLogic: CellLogic): Engine = DefaultEngine(cellLogic = cellLogic)

}
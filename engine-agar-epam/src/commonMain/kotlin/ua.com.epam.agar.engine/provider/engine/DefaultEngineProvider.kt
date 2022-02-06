package ua.com.epam.agar.engine.provider.engine

import ua.com.epam.agar.engine.provider.host.DefaultHostProvider
import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic
import ua.com.epam.agar.hackathon.core.game.Engine
import ua.com.epam.agar.hackathon.core.game.engine.provider.EngineProvider
import ua.com.epam.agar.hackathon.engine.DefaultEngine

class DefaultEngineProvider : EngineProvider {
    override fun provideEngine(cellLogic: CellLogic): Engine =
        DefaultEngine(cellLogic = cellLogic, DefaultHostProvider())
}
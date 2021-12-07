package com.ua.epam.agar.io.hackathon.core.game.engine

import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic
import com.ua.epam.agar.io.hackathon.core.game.Engine
import com.ua.epam.agar.io.hackathon.core.game.engine.provider.EngineProvider
import kotlinx.coroutines.*

//TODO should be moved outside
class InternalGameEngine(private val engineProvider: EngineProvider) : GameEngine {

    private val scope: CoroutineScope =
        CoroutineScope(Job() + Dispatchers.Main + CoroutineExceptionHandler(handler = { _, throwable ->
            throw throwable
        }))
    private lateinit var engine: Engine

    override fun initialize(cellLogic: CellLogic) {
        require(!this::engine.isInitialized) {
            "Engine is already initialized!"
        }
        engine = engineProvider.provideEngine(cellLogic)
    }

    override fun startGame(roomId: String) {
        checkInitCondition()
        scope.launch {
            engine.connectToRoom(roomId)
            engine.startGame()
        }
    }

    override fun stopGame() {
        checkInitCondition()
        scope.launch {
            engine.stopGame()
        }
        scope.coroutineContext.cancelChildren()
    }

    private fun checkInitCondition(message: String = "Game engine should be initialized before!") =
        require(this::engine.isInitialized) {
            message
        }
}
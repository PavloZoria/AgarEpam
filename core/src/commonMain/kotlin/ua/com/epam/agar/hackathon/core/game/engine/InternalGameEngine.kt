package ua.com.epam.agar.hackathon.core.game.engine

import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic
import ua.com.epam.agar.hackathon.core.game.Engine
import ua.com.epam.agar.hackathon.core.game.engine.provider.EngineProvider
import kotlinx.coroutines.*

class InternalGameEngine(private val engineProvider: EngineProvider) : GameEngine {

    private val scope: CoroutineScope =
        CoroutineScope(Job() + Dispatchers.Main + CoroutineExceptionHandler(handler = { _, throwable ->
            // throw Exception("Some exception were unhandled! Cause:\n ${throwable.stackTraceToString()}")
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
        scope.launch(Dispatchers.Default) {
            engine.connectToRoom(roomId)
            engine.startGame()
        }
    }

    override fun stopGame() {
        checkInitCondition()
        scope.launch(Dispatchers.Default) {
            engine.stopGame()
            scope.coroutineContext.cancelChildren()
        }
    }

    private fun checkInitCondition(message: String = "Game engine should be initialized before!") =
        require(this::engine.isInitialized) {
            message
        }
}
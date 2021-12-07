package com.ua.agar.io.hackathon.engine

import com.ua.agar.io.hackathon.api.connectToApiUsingWS
import com.ua.agar.io.hackathon.api.game.GameRepository
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic
import com.ua.epam.agar.io.hackathon.core.game.Engine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext

class DefaultEngine(private val cellLogic: CellLogic) : Engine {
    private lateinit var gameRepository: GameRepository

    override suspend fun connectToRoom(roomId: String) {
        runCatching {
            val connectToApiUsingWS = connectToApiUsingWS()
            gameRepository = connectToApiUsingWS.gameApi
            configure(roomId)
        }.onFailure {
            throw Exception("Error happened during connecting to the room! Cause:\n ${it.stackTraceToString()}")
        }
    }

    override suspend fun startGame() = withContext(Dispatchers.Default) {
        checkEngineCondition()
        playTheGame()
    }

    override suspend fun configure(roomId: String) = withContext(Dispatchers.Default) {
        checkEngineCondition()
        runCatching {
            val gameConfig = gameRepository.connectToRoom(roomId)
            cellLogic.configure(gameConfig)
        }.onFailure {
            throw Exception("Error happened during configuration! Cause:\n ${it.stackTraceToString()}")
        }
        Unit
    }

    override suspend fun stopGame() = withContext(Dispatchers.Default) {
        checkEngineCondition()
        TODO("Not yet implemented")
        //close room
        //cancel websocket
    }

    private suspend fun playTheGame() = withContext(Dispatchers.Default) {
        runCatching {
            checkEngineCondition()
            //socket
            //validation
            //get map`s state
//        send stateUpdates

            //send stateUpdates to a backend
            var mapState: MapState = gameRepository.mapState()
            while (isActive) {
                val myCells = emptyList<MyCell>()//TODO need
                val desiredCellsState = cellLogic.handleGameUpdate(myCells, mapState)
                mapState = gameRepository.gameTurn(desiredCellsState)
            }
        }.onFailure {
            throw Exception("Error happened during the game in progress! Cause:\n ${it.stackTraceToString()}")
        }
        Unit
    }

    private fun checkEngineCondition() {
        require(this::gameRepository.isInitialized) { "connectToRoom method should be called before" }
    }
}
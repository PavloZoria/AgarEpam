package com.ua.agar.io.hackathon.engine

import com.ua.agar.io.hackathon.api.game.socket.GameDataCombinedRepository
import com.ua.agar.io.hackathon.api.game.socket.GameDataRepository
import com.ua.agar.io.hackathon.api.game.socket.GameWebInteractor
import com.ua.agar.io.hackathon.api.game.socket.GameWebSocketAsAPI
import com.ua.agar.io.hackathon.api.game.socket.ktor.KtorWebSocket
import com.ua.agar.io.hackathon.api.game.socket.mapper.WebSocketModelMapper
import com.ua.agar.io.hackathon.api.game.storage.map.ComparatorMapStorage
import com.ua.agar.io.hackathon.api.game.storage.map.LocalMapStateStorage
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic
import com.ua.epam.agar.io.hackathon.core.game.Engine
import com.ua.epam.agar.io.hackathon.core.printLine
import com.ua.epam.agar.io.hackathon.core.repository.DefaultGameRepository
import com.ua.epam.agar.io.hackathon.core.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DefaultEngine(private val cellLogic: CellLogic) : Engine {
    private var startedJob: Job? = null
    private val webSocket = KtorWebSocket("45.77.67.171", modelMapper = WebSocketModelMapper())
    private val gameDataRepository: GameDataRepository = GameWebSocketAsAPI(webSocket)

    private val mapStorage = ComparatorMapStorage(storage = LocalMapStateStorage())
    private val gameDataCombined = GameDataCombinedRepository(gameDataRepository, mapStorage = mapStorage)
    private val webSocketRepo = GameWebInteractor(gameDataCombined)

    private val gameRepository: GameRepository = DefaultGameRepository(webSocketRepo)

    override suspend fun connectToRoom(roomId: String) {
        printLine("connectToRoom: begin...")
        runCatching {
            gameDataRepository.connectToTransport()
            configure(roomId, true)//TODO for testing purpose
            printLine("connectToRoom: complete...")
        }.onFailure {
            throw Exception("Error happened during connecting to the room! Cause:\n ${it.stackTraceToString()}")
        }
    }

    override suspend fun startGame() = withContext(Dispatchers.Default) {
        playTheGame()
    }

    override suspend fun configure(roomId: String, isTrainingRoom: Boolean) = withContext(Dispatchers.Default) {
        printLine("configure: begin...")
        runCatching {
            val gameConfig = gameRepository.connectToRoom(roomId)
            cellLogic.configure(gameConfig)
            printLine("configure: complete...")
        }.onFailure {
            printLine("configure: failed...")
            throw Exception("Error happened during configuration! Cause:\n ${it.stackTraceToString()}")
        }
        Unit
    }

    override suspend fun stopGame() = withContext(Dispatchers.Default) {
        printLine("stopGame")
        runCatching {
            gameDataRepository.disconnectFromTransport("Stop game!")
            startedJob?.cancel()
            printLine("stopGame: finished!")
        }.onFailure {
            throw Exception("Error happened during stopping the game! Cause:\n ${it.stackTraceToString()}")
        }
        //close room
        //cancel websocket
        Unit
    }

    private suspend fun playTheGame() = withContext(Dispatchers.Default) {
        runCatching {
            startedJob = launch {
                var mapState: MapState = gameRepository.mapState()
                while (startedJob?.isActive == true) {
                    printLine("playTheGame: Game turn")
                    if (mapState.myCells.isEmpty()) {
                        printLine("You are dead!")
                        stopGame()
                        break
                    }

                    val desiredCellsState = cellLogic.handleGameUpdate(mapState)
                    mapState = gameRepository.gameTurn(desiredCellsState)
                }
            }
        }.onFailure {
            throw Exception("Error happened during the game in progress! Cause:\n ${it.stackTraceToString()}")
        }
        Unit
    }
}
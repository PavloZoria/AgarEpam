package ua.com.epam.agar.hackathon.engine

import ua.com.epam.agar.hackathon.api.game.socket.game.GameDataCombinedRepository
import ua.com.epam.agar.hackathon.api.game.socket.game.GameDataRepository
import ua.com.epam.agar.hackathon.api.game.socket.game.GameWebSocketInteractor
import ua.com.epam.agar.hackathon.api.game.socket.game.GameWebSocketAsAPI
import ua.com.epam.agar.hackathon.api.game.socket.ktor.KtorWebSocket
import ua.com.epam.agar.hackathon.api.game.socket.mapper.WebSocketModelMapper
import ua.com.epam.agar.hackathon.api.game.storage.map.ComparatorMapStorage
import ua.com.epam.agar.hackathon.api.game.storage.map.LocalMapStateStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.epam.agar.hackathon.api.game.socket.game.tick.DefaultGameTickHandler
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic
import ua.com.epam.agar.hackathon.core.game.Engine
import ua.com.epam.agar.hackathon.core.printLine
import ua.com.epam.agar.hackathon.core.repository.DefaultGameRepository
import ua.com.epam.agar.hackathon.core.repository.GameRepository

class DefaultEngine(private val cellLogic: CellLogic) : Engine {
    private var startedJob: Job? = null
    private val webSocket = KtorWebSocket("45.77.67.171", modelMapper = WebSocketModelMapper())
    private val gameDataRepository: GameDataRepository = GameWebSocketAsAPI(webSocket)

    private val mapStorage = ComparatorMapStorage(storage = LocalMapStateStorage())
    private val gameDataCombined =
        GameDataCombinedRepository(gameDataRepository, mapStorage = mapStorage, tickHandler = DefaultGameTickHandler(webSocket))
    private val webSocketRepo = GameWebSocketInteractor(gameDataCombined)

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
        if (startedJob?.isActive == true) {
            printLine("The game is already started! Stop the game in order to start it!")
            return@withContext
        }
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
            gameDataCombined.disconnectFromTransport("Stop game!")
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
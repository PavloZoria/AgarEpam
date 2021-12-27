package ua.com.epam.agar.hackathon.engine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.direct
import org.kodein.di.instance
import ua.com.epam.agar.hackathon.api.game.socket.caching.CachingGameDataRepository
import ua.com.epam.agar.hackathon.api.game.socket.game.GameDataRepository
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic
import ua.com.epam.agar.hackathon.core.game.Engine
import ua.com.epam.agar.hackathon.core.printLine
import ua.com.epam.agar.hackathon.core.repository.GameRepository
import ua.com.epam.agar.hackathon.di.kodeinContainer

class DefaultEngine(private val cellLogic: CellLogic) : Engine {
    private var startedJob: Job? = null

    private val gameDataCombined = kodeinContainer.direct.instance<CachingGameDataRepository>()
    private val gameRepository: GameRepository = kodeinContainer.direct.instance()

    override suspend fun connectToRoom(roomId: String) {
        printLine("connectToRoom: begin...")
        runCatching {
            gameDataCombined.connectToTransport()
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
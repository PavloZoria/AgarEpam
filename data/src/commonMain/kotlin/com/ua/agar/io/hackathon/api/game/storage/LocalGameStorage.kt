package com.ua.agar.io.hackathon.api.game.storage

import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig

class LocalGameStorage constructor(
    private val gameConfigStorage: Storage<GameConfig>,
    private val mapStorage: Storage<MapState>,
    private val desiredCellsStateStorage: Storage<DesiredCellsState>,
)/* : GameStorage {
//    private var gameConfig: GameConfig? = null
//    private var desiredCellsState: DesiredCellsState? = null
//
//    override suspend fun gameConfig(gameConfig: GameConfig) {
//        this.gameConfig = gameConfig
//    }
//
//    override suspend fun gameConfig(): GameConfig? = gameConfig
//
//    override suspend fun mapState(mapState: MapState) {
//        this.mapState = mapState
//    }
//
//    override suspend fun mapState(): MapState? = mapState
//
//    override suspend fun gameTurn(desiredCellsState: DesiredCellsState?) {
//        this.desiredCellsState = desiredCellsState
//    }
//
//    override suspend fun gameTurn(): DesiredCellsState? = desiredCellsState

}*/
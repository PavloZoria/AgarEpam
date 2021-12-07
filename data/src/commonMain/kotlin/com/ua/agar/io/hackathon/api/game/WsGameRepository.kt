package com.ua.agar.io.hackathon.api.game

import com.ua.agar.io.hackathon.data.config.GameConfigModel
import com.ua.agar.io.hackathon.data.mapper.CellConfigMapper
import com.ua.agar.io.hackathon.data.mapper.FoodConfigMapper
import com.ua.agar.io.hackathon.data.mapper.GameConfigMapper
import com.ua.agar.io.hackathon.data.mapper.MapConfigMapper
import com.ua.agar.io.hackathon.serialization.decodeFromPayload
import com.ua.agar.io.hackathon.serialization.encodeToPayload
import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.game.config.CellConfig
import com.ua.epam.agar.io.hackathon.core.game.config.FoodConfig
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig
import com.ua.epam.agar.io.hackathon.core.game.config.MapConfig
import io.rsocket.kotlin.RSocket
import kotlinx.coroutines.flow.first
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.protobuf.ProtoBuf

class WsGameRepository @OptIn(ExperimentalSerializationApi::class) constructor(
    private val rSocket: RSocket,
    private val proto: ProtoBuf,
    private val gameConfigMapper: GameConfigMapper
) : GameRepository {

    override suspend fun connectToRoom(room: String): GameConfig {
        val decodeFromPayload = proto.decodeFromPayload<GameConfigModel>(
            rSocket.requestStream(
                proto.encodeToPayload(route = "connect_to_room", room)
            ).first()
        )
        return gameConfigMapper.mapFrom(decodeFromPayload)
    }

    override suspend fun mapState(): MapState {
        TODO("Not yet implemented")
    }

    override suspend fun gameConfig(): GameConfig {
        val decodeFromPayload: GameConfigModel = proto.decodeFromPayload<GameConfigModel>(
            rSocket.requestResponse(
                proto.encodeToPayload(route = "get_configs", Any())
            )
        )
        return gameConfigMapper.mapFrom(decodeFromPayload)
    }

    override suspend fun gameTurn(desiredCellsState: DesiredCellsState?): MapState {
        TODO("Not yet implemented")
    }
}
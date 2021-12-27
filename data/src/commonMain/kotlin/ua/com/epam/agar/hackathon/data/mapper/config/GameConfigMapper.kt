package ua.com.epam.agar.hackathon.data.mapper.config

import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.GameConfigModel
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.core.game.config.GameConfig

internal class GameConfigMapper(
    private val cellConfigMapper: CellConfigMapper,
    private val mapConfigMapper: MapConfigMapper,
    private val foodConfigMapper: FoodConfigMapper
) : Mapper<GameConfig, GameConfigModel> {
    override fun mapFrom(item: GameConfigModel): GameConfig =
        GameConfig(
            tickTime = item.tickTime,
            tickLimit = item.tickLimit,
            cellConfig = cellConfigMapper.mapFrom(item.cellConfig),
            mapConfig = mapConfigMapper.mapFrom(item.mapConfig),
            foodConfig = foodConfigMapper.mapFrom(item.foodConfig)
        )

    override fun mapTo(item: GameConfig): GameConfigModel =
        GameConfigModel(
            tickTime = item.tickTime,
            tickLimit = item.tickLimit,
            cellConfig = cellConfigMapper.mapTo(item.cellConfig),
            mapConfig = mapConfigMapper.mapTo(item.mapConfig),
            foodConfig = foodConfigMapper.mapTo(item.foodConfig)
        )
}
package com.ua.agar.io.hackathon.data.mapper

import com.ua.agar.io.hackathon.data.config.GameConfigModel
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig

class GameConfigMapper(
    private val cellConfigMapper: CellConfigMapper,
    private val mapConfigMapper: MapConfigMapper,
    private val foodConfigMapper: FoodConfigMapper
) : Mapper<GameConfig, GameConfigModel> {
    override fun mapFrom(item: GameConfigModel): GameConfig =
        GameConfig(
            cellConfig = cellConfigMapper.mapFrom(item.cellConfig),
            mapConfig = mapConfigMapper.mapFrom(item.mapConfig),
            foodConfig = foodConfigMapper.mapFrom(item.foodConfig)
        )

    override fun mapTo(item: GameConfig): GameConfigModel =
        GameConfigModel(
            cellConfig = cellConfigMapper.mapTo(item.cellConfig),
            mapConfig = mapConfigMapper.mapTo(item.mapConfig),
            foodConfig = foodConfigMapper.mapTo(item.foodConfig)
        )
}
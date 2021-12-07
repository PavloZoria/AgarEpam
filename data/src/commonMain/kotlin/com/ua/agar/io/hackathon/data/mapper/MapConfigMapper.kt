package com.ua.agar.io.hackathon.data.mapper

import com.ua.agar.io.hackathon.data.config.MapConfigModel
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper
import com.ua.epam.agar.io.hackathon.core.game.config.MapConfig

class MapConfigMapper : Mapper<MapConfig, MapConfigModel> {
    override fun mapFrom(item: MapConfigModel): MapConfig = MapConfig(height = item.height, width = item.width)

    override fun mapTo(item: MapConfig): MapConfigModel = MapConfigModel(height = item.height, width = item.width)
}
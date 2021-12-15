package ua.com.epam.agar.hackathon.data.mapper.config

import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.core.game.config.MapConfig
import ua.com.epam.agar.hackathon.data.config.MapConfigModel

internal class MapConfigMapper : Mapper<MapConfig, MapConfigModel> {
    override fun mapFrom(item: MapConfigModel): MapConfig = MapConfig(height = item.height, width = item.width)

    override fun mapTo(item: MapConfig): MapConfigModel = MapConfigModel(height = item.height, width = item.width)
}
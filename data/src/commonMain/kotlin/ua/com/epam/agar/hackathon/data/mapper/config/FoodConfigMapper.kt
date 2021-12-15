package ua.com.epam.agar.hackathon.data.mapper.config

import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.core.game.config.FoodConfig
import ua.com.epam.agar.hackathon.data.config.FoodConfigModel

internal class FoodConfigMapper : Mapper<FoodConfig, FoodConfigModel> {
    override fun mapFrom(item: FoodConfigModel): FoodConfig = FoodConfig(item.mass)

    override fun mapTo(item: FoodConfig): FoodConfigModel = FoodConfigModel(item.mass)
}
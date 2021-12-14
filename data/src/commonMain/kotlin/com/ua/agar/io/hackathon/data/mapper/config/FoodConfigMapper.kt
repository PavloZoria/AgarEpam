package com.ua.agar.io.hackathon.data.mapper.config

import com.ua.agar.io.hackathon.data.config.FoodConfigModel
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper
import com.ua.epam.agar.io.hackathon.core.game.config.FoodConfig

internal class FoodConfigMapper : Mapper<FoodConfig, FoodConfigModel> {
    override fun mapFrom(item: FoodConfigModel): FoodConfig = FoodConfig(item.mass)

    override fun mapTo(item: FoodConfig): FoodConfigModel = FoodConfigModel(item.mass)
}
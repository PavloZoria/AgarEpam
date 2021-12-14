package com.ua.agar.io.hackathon.data.mapper.map_state.food

import com.ua.agar.io.hackathon.data.FoodModel
import com.ua.agar.io.hackathon.data.mapper.map_state.cell.PositionMapper
import com.ua.epam.agar.io.hackathon.core.entity.Food
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper

internal class FoodMapper(private val positionMapper: PositionMapper = PositionMapper()) : Mapper<Food, FoodModel> {
    override fun mapFrom(item: FoodModel): Food = with(item) {
        Food(id ?: "", 0.2f, positionMapper.mapFrom(position))
    }

    override fun mapTo(item: Food): FoodModel = with(item) {
        FoodModel(id ?: "", positionMapper.mapTo(position))
    }
}
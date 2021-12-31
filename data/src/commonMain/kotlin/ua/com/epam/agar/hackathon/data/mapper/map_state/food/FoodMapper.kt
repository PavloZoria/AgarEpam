package ua.com.epam.agar.hackathon.data.mapper.map_state.food

import ua.com.epam.agar.hackathon.core.entity.Food
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.mapper.map_state.cell.PositionMapper

internal class FoodMapper(private val positionMapper: PositionMapper = PositionMapper()) : Mapper<Food, FoodModel> {
    override fun mapFrom(item: FoodModel): Food = with(item) {
        Food(id, 0.2f, positionMapper.mapFrom(position))
    }

    override fun mapTo(item: Food): FoodModel = with(item) {
        FoodModel(id, positionMapper.mapTo(position))
    }
}
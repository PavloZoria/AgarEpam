package ua.com.epam.agar.hackathon.api.game.storage.local

import io.kotest.matchers.shouldBe
import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModel
import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModelMapper
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.api.game.storage.map.LocalMapStateStorage
import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.cell.CellModel
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel
import kotlin.test.Test

internal class LocalMapStateModelMapperTest {
    private val localMapStateModelMapper = LocalMapStateModelMapper()

    private val localMapStateStorage = LocalMapStateStorage()
    private val testId1 = "id1"
    val testId2 = "id2"
    val testId3 = "id3"

    private val position1 = PositionModel(0f, 0f)
    private val velocity = VelocityModel(0f, 0f)

    private val testCell1: CellModel =
        CellModel(
            cellId = testId1,
            player = testId1,
            own = true,
            mass = 1f,
            radius = 1f,
            position = position1,
            velocity = velocity,
            availableEnergy = 1f,
            canSplit = false,
            canMerge = true,
            speed = 1f,
            maxSpeed = 1f
        )
    private val testFood: FoodModel =
        FoodModel(
            id = testId1,
            position = position1,
            deleted = false
        )

    private val tickNumber = 150
    private val lastReceivedTick = 149

    private val cellsOnMap = hashMapOf(testCell1.cellId to testCell1)
    private val food = hashMapOf(testFood.id to testFood)

    private val localMapStateModel =
        LocalMapStateModel(
            tickNumber,
            lastReceivedTick,
            cellsOnMap,
            food
        )
    private val mapStateModel = MapStateModel(
        tickNumber,
        lastReceivedTick,
        cellsOnMap.values,
        food.values
    )

    @Test
    fun testMapTo() {
        localMapStateModelMapper.mapTo(localMapStateModel) shouldBe mapStateModel
    }

    @Test
    fun testMapFrom() {
        localMapStateModelMapper.mapFrom(mapStateModel) shouldBe localMapStateModel
    }
}
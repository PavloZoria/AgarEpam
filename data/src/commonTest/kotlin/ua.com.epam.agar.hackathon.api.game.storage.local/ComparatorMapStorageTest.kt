package ua.com.epam.agar.hackathon.api.game.storage.local

import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModel
import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModelMapper
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.api.game.storage.map.ComparatorMapStorage
import ua.com.epam.agar.hackathon.api.game.storage.map.LocalMapStateStorage
import ua.com.epam.agar.hackathon.api.game.storage.map.NewStateMapStateModel
import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.cell.CellModel
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel
import kotlin.test.Test

internal class ComparatorMapStorageTest {
    private val localMapStateModelMapper = LocalMapStateModelMapper()

    private val localMapStateStorage = LocalMapStateStorage()
    private val testId1 = "id1"
    val testId2 = "id2"
    val testId3 = "id3"

    private val position1 = PositionModel(0f, 0f)
    private val position2 = PositionModel(10f, 10f)
    private val velocity1 = VelocityModel(0f, 0f)
    private val velocity2 = VelocityModel(10f, 10f)

    private val testCell1: CellModel =
        CellModel(
            cellId = testId1,
            player = testId1,
            own = true,
            mass = 1f,
            radius = 1f,
            position = position1,
            velocity = velocity1,
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
    private val originalData = MapStateModel(
        tickNumber,
        lastReceivedTick,
        cellsOnMap.values,
        food.values
    )
    private val storage: ComparatorMapStorage =
        ComparatorMapStorage(LocalMapStateStorage(), mapper = LocalMapStateModelMapper())

    @Test
    fun testLocalStorageReturnTheSameValueAfterInitialSet() = runBlocking {
        storage.set(originalData)
        val retrieved = storage.get()
        retrieved shouldBe originalData
    }

    @Test
    fun testLocalStorageReturnTheSameValueAfterUpdate_TickNumber() = runBlocking {
        val newTickNumber = 12
        storage.invalidate()
        storage.set(originalData.copy())
        storage.set(NewStateMapStateModel(tickNumber = newTickNumber))
        val retrieved = storage.get()
        retrieved shouldBe originalData.copy(tickNumber = newTickNumber)
    }

    @Test
    fun testLocalStorageReturnTheSameValueAfterUpdate_LastReceivedTick() = runBlocking {
        val newTickNumber = 12
        storage.invalidate()
        storage.set(originalData.copy())
        storage.set(NewStateMapStateModel(tickNumber, lastReceivedTick = newTickNumber))
        val retrieved = storage.get()
        retrieved shouldBe originalData.copy(lastReceivedTick = newTickNumber)
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_Position() = runBlocking {
        testCellDataUpdate(
            CellModel(cellId = testId1, position = position2),
            originalData.cellsOnMap!!.first().copy(position = position2)
        )
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_Velocity() = runBlocking {
        testCellDataUpdate(
            CellModel(cellId = testId1, velocity = velocity2),
            originalData.cellsOnMap!!.first().copy(velocity = velocity2)
        )
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_Radius() = runBlocking {
        val newRadius = 10f
        val cells = CellModel(cellId = testId1, radius = newRadius)
        val expectedResult = originalData.cellsOnMap!!.first().copy(radius = newRadius)
        testCellDataUpdate(cells, expectedResult)
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_Mass() = runBlocking {
        val newMass = 10f
        val cells = CellModel(cellId = testId1, mass = newMass)
        val expectedResult = originalData.cellsOnMap!!.first().copy(mass = newMass)
        testCellDataUpdate(cells, expectedResult)
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_AvailableEnergy() = runBlocking {
        val availableEnergy = 10f
        val cells = CellModel(cellId = testId1, availableEnergy = availableEnergy)
        val expectedResult = originalData.cellsOnMap!!.first().copy(availableEnergy = availableEnergy)
        testCellDataUpdate(cells, expectedResult)
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_CanSplit() = runBlocking {
        val canSplit = true
        val cells = CellModel(cellId = testId1, canSplit = canSplit)
        val expectedResult = originalData.cellsOnMap!!.first().copy(canSplit = canSplit)
        testCellDataUpdate(cells, expectedResult)
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_canMerge() = runBlocking {
        val canMerge = true
        val cells = CellModel(cellId = testId1, canMerge = canMerge)
        val expectedResult = originalData.cellsOnMap!!.first().copy(canMerge = canMerge)
        testCellDataUpdate(cells, expectedResult)
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_speed() = runBlocking {
        val speed = 10f
        val cells = CellModel(cellId = testId1, speed = speed)
        val expectedResult = originalData.cellsOnMap!!.first().copy(speed = speed)
        testCellDataUpdate(cells, expectedResult)
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_maxSpeed() = runBlocking {
        val maxSpeed = 10f
        val cells = CellModel(cellId = testId1, maxSpeed = maxSpeed)
        val expectedResult = originalData.cellsOnMap!!.first().copy(maxSpeed = maxSpeed)
        testCellDataUpdate(cells, expectedResult)
    }

    @Test
    fun testLocalStorageReturnAppropriateValueAfterUpdate_eatEfficiency() = runBlocking {
        val eatEfficiency = 10f
        val cells = CellModel(cellId = testId1, eatEfficiency = eatEfficiency)
        val expectedResult = originalData.cellsOnMap!!.first().copy(eatEfficiency = eatEfficiency)
        testCellDataUpdate(cells, expectedResult)
    }

    @Test
    fun testCellRemovesFromStorageAfter_deleted_is_true() = runBlocking {
        val cells = CellModel(cellId = testId1, deleted = true)
        testCellDataUpdate(cells, null)
    }

    private suspend fun testCellDataUpdate(cells: CellModel, expectedCells: CellModel?) {
        storage.invalidate()
        storage.set(originalData.copy())
        storage.set(
            NewStateMapStateModel(
                tickNumber,
                cellsOnMap = listOf(cells)
            )
        )
        val retrieved = storage.get()
        val expectedResult = originalData.copy(cellsOnMap = listOfNotNull(expectedCells))
        retrieved shouldBe expectedResult
    }
}
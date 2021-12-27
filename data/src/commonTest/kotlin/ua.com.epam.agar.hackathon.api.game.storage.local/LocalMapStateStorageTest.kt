package ua.com.epam.agar.hackathon.api.game.storage.local

import io.kotest.assertions.failure
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import kotlinx.coroutines.runBlocking
import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModel
import ua.com.epam.agar.hackathon.api.game.storage.map.LocalMapStateStorage
import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.cell.CellModel
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel
import kotlin.test.Test
import kotlin.test.fail

internal class LocalMapStateStorageTest {

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

    @Test
    fun testLocalStorageReturnTheSameValue() = runBlocking {
        val data = LocalMapStateModel(150, 149, hashMapOf(), hashMapOf())
        localMapStateStorage.set(data)
        localMapStateStorage.get() shouldBe data

    }

    @Test
    fun testWithACell() = runBlocking {
        val data1 = LocalMapStateModel(150, 149, hashMapOf(testCell1.cellId to testCell1), hashMapOf())
        localMapStateStorage.set(data1)
        localMapStateStorage.get() shouldBe data1
    }

    @Test
    fun testWithAFood() = runBlocking {
        val data1 =
            LocalMapStateModel(
                150,
                149,
                hashMapOf(),
                hashMapOf(testFood.id to testFood)
            )
        localMapStateStorage.set(data1)
        localMapStateStorage.get() shouldBe data1
    }

    @Test
    fun testWithCellAndFood() = runBlocking {
        val data1 =
            LocalMapStateModel(
                150,
                149,
                hashMapOf(testCell1.cellId to testCell1),
                hashMapOf(testFood.id to testFood)
            )
        localMapStateStorage.set(data1)
        localMapStateStorage.get() shouldBe data1
    }

    @Test
    fun testExistReturnTrue() = runBlocking {
        val data1 =
            LocalMapStateModel(
                150,
                149,
                hashMapOf(testCell1.cellId to testCell1),
                hashMapOf(testFood.id to testFood)
            )
        localMapStateStorage.set(data1)
        localMapStateStorage.exist() shouldBe true
    }

    @Test
    fun testExistReturnFalseWhenNoData() = runBlocking {
        val localMapStateStorage = LocalMapStateStorage()
        localMapStateStorage.exist() shouldBe false
    }

    @Test
    fun testGetFailsWithExceptionWhenNoData() = runBlocking {
        val localMapStateStorage = LocalMapStateStorage()
        try {
            localMapStateStorage.get()
        } catch (e: Exception) {
            e should beInstanceOf<NoDataExistException>()
        }
        Unit
    }
}
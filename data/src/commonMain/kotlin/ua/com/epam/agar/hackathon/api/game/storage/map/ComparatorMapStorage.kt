package ua.com.epam.agar.hackathon.api.game.storage.map

import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModel
import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModelMapper
import ua.com.epam.agar.hackathon.api.game.storage.Storage
import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.cell.CellModel
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.core.printLine
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel

internal typealias NewStateMapStateModel = MapStateModel

internal class ComparatorMapStorage(
    private val storage: Storage<LocalMapStateModel>,
    private val mapper: LocalMapStateModelMapper
) : Storage<NewStateMapStateModel> {

    override suspend fun set(value: NewStateMapStateModel) {
        val newState = value
        val mapStateModel = if (storage.exist()) {
            val dataFromStorage = storage.get()
            val mapStateToBeRefreshed =
                dataFromStorage.copy(
                    tickNumber = newState.tickNumber,
                    lastReceivedTick = newState.lastReceivedTick ?: dataFromStorage.lastReceivedTick
                ).apply {
                    println("ComparatorMapStorage: set -> newState: $newState \n savedState: $this")
                    cellsOnMap?.refreshCells(newState)
                    food?.refreshFood(newState)
                }
            mapStateToBeRefreshed
        } else {
            mapper.mapFrom(newState)
        }
        storage.set(mapStateModel)
    }

    override suspend fun get(): NewStateMapStateModel = mapper.mapTo(storage.get())

    override suspend fun exist(): Boolean = storage.exist()

    override fun invalidate() = storage.invalidate()


    private fun MutableMap<String, FoodModel>.refreshFood(newState: NewStateMapStateModel) {
        val old = this
        old.removeDeletedFood(newState)

        val freshNotDeletedFood = newState.notDeletedFood()

        freshNotDeletedFood?.map { new ->
            new.id to (old.findFoodById(new.id)?.safeCopy(new) ?: new)
        }?.run {
            putAll(this)
        }
    }

    private fun MutableMap<String, CellModel>.refreshCells(
        newState: NewStateMapStateModel
    ) {
        val old = this
        old.removeDeletedCell(newState)

        newState
            .notDeletedCell()
            ?.map { new ->
                new.cellId to (old.findCellModelById(new.cellId)?.safeCopy(new) ?: new)
            }?.run {
                putAll(this)
            }
    }

    private fun Map<String, CellModel>.findCellModelById(cellId: String?) = get(cellId)

    private fun Map<String, FoodModel>.findFoodById(id: String?) = get(id)

    private fun MutableMap<String, CellModel>.removeDeletedCell(
        newState: NewStateMapStateModel,
    ) = newState
        .cellsOnMap
        ?.filter {
            printLine("Found to delete cell: ${it.deleted}")
            it.deleted == true
        }
        ?.forEach {
            val deleted = this.remove(it.cellId)
            printLine("Deleted: $deleted, $it")
        }

    private fun MutableMap<String, FoodModel>.removeDeletedFood(
        newState: NewStateMapStateModel,
    ) = newState
        .food
        ?.filter {
            it.deleted == true
        }
        ?.forEach {
            this@removeDeletedFood.remove(it.id)
        }

    private fun NewStateMapStateModel.notDeletedFood() =
        food?.filter {
            it.deleted != true
        }

    private fun NewStateMapStateModel.notDeletedCell() =
        cellsOnMap?.filter {
            it.deleted != true
        }
}


internal fun CellModel.safeCopy(new: CellModel): CellModel {
    val old = this
    return old.copy(
        mass = new.mass ?: old.mass,
        radius = new.radius ?: old.radius,
        position = old.position!!.safeCopy(new.position),
        velocity = old.velocity!!.safeCopy(new.velocity),
        availableEnergy = new.availableEnergy ?: old.availableEnergy,
        canSplit = new.canSplit ?: old.canSplit,
        canMerge = new.canMerge ?: old.canMerge,
        speed = new.speed ?: old.speed,
        maxSpeed = new.maxSpeed ?: old.maxSpeed,
        eatEfficiency = new.eatEfficiency ?: old.eatEfficiency,
        deleted = new.deleted ?: old.deleted,
        own = old.own,
        player = old.player,
    )
}

internal fun PositionModel.safeCopy(new: PositionModel?): PositionModel {
    val old = this
    return old.copy(
        x = new?.x ?: old.x,
        y = new?.y ?: old.y
    )
}

internal fun VelocityModel.safeCopy(new: VelocityModel?): VelocityModel {
    val old = this
    return old.copy(
        x = new?.x ?: old.x,
        y = new?.y ?: old.y
    )
}

internal fun FoodModel.safeCopy(new: FoodModel): FoodModel {
    val old = this
    return FoodModel(
        id = old.id,
        position = this.position!!.safeCopy(new.position)
    )
}
package ua.com.epam.agar.hackathon.api.game.storage.map

import ua.com.epam.agar.hackathon.api.game.storage.Storage
import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel
import ua.com.epam.agar.hackathon.data.cell.CellModel
import ua.com.epam.agar.hackathon.api.game.socket.model.data.MapStateModel
import ua.com.epam.agar.hackathon.core.printLine

internal typealias NewStateMapStateModel = MapStateModel

internal class ComparatorMapStorage(private val storage: Storage<MapStateModel>) : Storage<NewStateMapStateModel> {

    override suspend fun set(newState: NewStateMapStateModel) {
        val mapStateModel = if (storage.exist()) {
            val mapStateToBeRefreshed = storage.get().copy(tickNumber = newState.tickNumber).apply {
                println("ComparatorMapStorage: set -> newState: $newState \n savedState: $this")
                cellsOnMap?.refreshCells(newState)
                food?.refreshFood(newState)
            }
            mapStateToBeRefreshed
        } else {
            newState
        }
        storage.set(mapStateModel)
    }

    override suspend fun get(): NewStateMapStateModel = storage.get()

    override suspend fun exist(): Boolean = storage.exist()

    override fun invalidate() = storage.invalidate()


    private fun HashSet<FoodModel>.refreshFood(newState: NewStateMapStateModel) {
        val old = this
        old.removeDeletedFood(newState)

        val freshNotDeletedFood = newState.notDeletedFood()

        freshNotDeletedFood?.map { new ->
            old.findFoodById(new.id)?.safeCopy(new) ?: new
        }?.run {
            addAll(this)
            forEach {
                if (old.contains(it)) {
                    old.remove(it)
                }
                old.add(it)
            }
        }
    }

    private fun HashSet<CellModel>.refreshCells(
        newState: NewStateMapStateModel
    ) {
        val old = this
        old.removeDeletedCell(newState)

        newState
            .notDeletedCell()
            ?.map { new ->
                old.findCellModelById(new.cellId)?.safeCopy(new) ?: new
            }?.run {
                addAll(this)
                forEach {
                    if (old.contains(it)) {
                        old.remove(it)
                    }
                    old.add(it)
                }
            }
    }

    private fun Iterable<CellModel>.findCellModelById(cellId: String?) =
        firstOrNull { it.cellId == cellId }

    private fun Iterable<FoodModel>.findFoodById(id: String?) =
        firstOrNull { it.id == id }

    private fun HashSet<CellModel>.removeDeletedCell(
        newState: NewStateMapStateModel,
    ) = newState
        .cellsOnMap
        ?.filter {
            printLine("Found to delete cell: ${it.deleted}")
            it.deleted == true
        }
        ?.forEach {
            val deleted = this.remove(it)
            printLine("Deleted: $deleted, $it")
        }

    private fun HashSet<FoodModel>.removeDeletedFood(
        newState: NewStateMapStateModel,
    ) = newState
        .food
        ?.filter {
            it.deleted == true
        }
        ?.forEach {
            this.remove(it)
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
    return CellModel(
        cellId = old.cellId,
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
        deleted = new.deleted ?: old.deleted ?: false,
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
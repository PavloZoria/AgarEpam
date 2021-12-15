package ua.com.epam.agar.hackathon.api.game.storage.map

import ua.com.epam.agar.hackathon.api.game.storage.Storage
import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel
import ua.com.epam.agar.hackathon.data.cell.CellModel
import ua.com.epam.agar.hackathon.api.game.socket.model.data.MapStateModel

internal typealias NewStateMapStateModel = MapStateModel

internal class ComparatorMapStorage(private val storage: Storage<MapStateModel>) : Storage<NewStateMapStateModel> {

    override suspend fun set(newState: NewStateMapStateModel) {
        storage.set(newState)
        return
//refreshing data isn't available from platform side
        val mapStateModel = if (storage.exist()) {
            val mapStateToBeRefreshed = storage.get().copy()
            println("ComparatorMapStorage: set -> newState: $newState \n savedState: $mapStateToBeRefreshed")

            with(mapStateToBeRefreshed.cellsOnMap) {
                removeDeletedCell(newState)
                map { old ->
                    newState.findCellModelById(old.cellId)?.let { new ->
                        old.safeCopy(new)
                    } ?: old
                }.run {
                    clear()
                    addAll(this)
                }
            }

            with(mapStateToBeRefreshed.food) {
                removeDeletedFood(newState)
                map { old ->
                    newState.findFoodById(old.id)?.let { new ->
                        old.safeCopy(new)
                    } ?: old
                }.run {
                    clear()
                    addAll(this)
                }
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

    private fun MapStateModel.findCellModelById(cellId: String?) =
        cellsOnMap.firstOrNull { it.cellId == cellId }

    private fun MapStateModel.findFoodById(id: String?) =
        food.firstOrNull { it.id == id }

    private fun ArrayList<CellModel>.removeDeletedCell(
        newState: NewStateMapStateModel,
    ) = newState.cellsOnMap.filter { it.deleted == true }.forEach {
        this.remove(it)
    }

    private fun ArrayList<FoodModel>.removeDeletedFood(
        newState: NewStateMapStateModel,
    ) = newState.food.filter { it.deleted == true }.forEach {
        this.remove(it)
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
        eatEfficiency = new.eatEfficiency ?: old.eatEfficiency,
        deleted = new.deleted ?: old.deleted
    )
}

internal fun PositionModel.safeCopy(new: PositionModel?): PositionModel {
    val old = this
    return old.copy(
        x = new?.x ?: old.x,
        y = new?.y ?: old.y)
}

internal fun VelocityModel.safeCopy(new: VelocityModel?): VelocityModel {
    val old = this
    return old.copy(
        x = new?.x ?: old.x,
        y = new?.y ?: old.y)
}

internal fun FoodModel.safeCopy(new: FoodModel): FoodModel {
    val old = this
    return old.copy(
        position = this.position!!.safeCopy(new.position),
        deleted = new.deleted ?: old.deleted)
}
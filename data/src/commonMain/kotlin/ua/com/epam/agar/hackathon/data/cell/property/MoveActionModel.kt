package ua.com.epam.agar.hackathon.data.cell.property

import kotlinx.serialization.Serializable

@Serializable
internal sealed class MoveActionModel {
    @Serializable
    object Split : MoveActionModel()

    @Serializable
    class Merge(val cellId: CellIdModel) : MoveActionModel()
}
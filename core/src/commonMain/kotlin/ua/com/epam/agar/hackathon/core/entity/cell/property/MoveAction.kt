package ua.com.epam.agar.hackathon.core.entity.cell.property

sealed class MoveAction {
    object Split : MoveAction()
    class Merge(val cellId: CellId) : MoveAction()
}
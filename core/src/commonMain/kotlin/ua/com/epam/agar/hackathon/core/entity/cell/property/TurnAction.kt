package ua.com.epam.agar.hackathon.core.entity.cell.property

sealed class TurnAction {
    object Split : TurnAction()
    class Merge(val cellId: CellId) : TurnAction()
}
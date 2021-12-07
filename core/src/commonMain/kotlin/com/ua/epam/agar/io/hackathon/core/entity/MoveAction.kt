package com.ua.epam.agar.io.hackathon.core.entity

sealed class MoveAction {
    object Split : MoveAction()
    class Merge(val cellId: CellId) : MoveAction()
}
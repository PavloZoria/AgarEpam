package com.ua.agar.io.hackathon.data

import kotlinx.serialization.Serializable

@Serializable
sealed class MoveActionModel {
    @Serializable
    object Split : MoveActionModel()

    @Serializable
    class Merge(val cellId: CellIdModel) : MoveActionModel()
}
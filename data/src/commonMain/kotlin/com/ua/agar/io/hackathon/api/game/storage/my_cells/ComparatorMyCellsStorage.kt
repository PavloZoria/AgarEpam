package com.ua.agar.io.hackathon.api.game.storage.my_cells

import com.ua.agar.io.hackathon.api.game.storage.Storage
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell

class ComparatorMyCellsStorage(private val storage: Storage<MyCell>) : Storage<MyCell> {
    override suspend fun set(value: MyCell) {
        TODO("Not yet implemented")
    }

    override suspend fun get(): MyCell? {
        TODO("Not yet implemented")
    }
}
package com.ua.agar.io.hackathon.data.mapper.map_state.cell

import com.ua.agar.io.hackathon.data.cell.CellModel
import com.ua.epam.agar.io.hackathon.core.entity.cell.AlienCell
import com.ua.epam.agar.io.hackathon.core.entity.cell.Cell
import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper

internal class CellMapper(
    private val myCellMapper: MyCellMapper = MyCellMapper(),
    private val alienCellMapper: AlienCellMapper = AlienCellMapper(),
) :
    Mapper<Cell, CellModel> {
    override fun mapFrom(item: CellModel): Cell = with(item) {
        if (own == true) {
            myCellMapper.mapFrom(this)
        } else {
            alienCellMapper.mapFrom(this)
        }
    }

    override fun mapTo(item: Cell): CellModel = with(item) {
        when (item) {
            is MyCell -> {
                myCellMapper.mapTo(this as MyCell)
            }
            is AlienCell -> {
                alienCellMapper.mapTo(this as AlienCell)
            }
            else -> {
                throw Exception("not supported type")
            }
        }
    }
}
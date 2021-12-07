package com.epam.agar.hackathon.agar_epam.app.game

import com.ua.epam.agar.io.hackathon.core.entity.cell.MyCell
import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.entity.public_api.CellLogic

class TestCell: CellLogic() {
    override fun handleGameUpdate(myCells: List<MyCell>, mapState: MapState): DesiredCellsState? {
        TODO("Not yet implemented")
    }
}
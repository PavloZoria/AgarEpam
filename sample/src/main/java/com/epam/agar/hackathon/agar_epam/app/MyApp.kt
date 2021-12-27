package com.epam.agar.hackathon.agar_epam.app

import ua.com.epam.agar.app.AgarApp
import ua.com.epam.agar.app.game.cell.ChaserDanCell
import ua.com.epam.agar.app.game.cell.FatJohnyCell
import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic

class MyApp: AgarApp() {
    override fun cellImplementation(): CellLogic = FatJohnyCell()
//    override fun cellImplementation(): CellLogic = ChaserDanCell()
}
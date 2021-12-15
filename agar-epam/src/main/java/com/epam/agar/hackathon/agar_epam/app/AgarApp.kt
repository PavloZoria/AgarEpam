package com.epam.agar.hackathon.agar_epam.app

import android.app.Application
import com.epam.agar.hackathon.agar_epam.app.game.cell.FatJohnyCell
import ua.com.epam.agar.engine.GameEngine

class AgarApp: Application() {
    override fun onCreate() {
        super.onCreate()
        GameEngine.initialize(FatJohnyCell())
        // GameEngine.initialize(ChaserDanCell())
    }
}
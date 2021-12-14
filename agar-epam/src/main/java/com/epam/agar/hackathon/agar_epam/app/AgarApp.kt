package com.epam.agar.hackathon.agar_epam.app

import android.app.Application
import com.epam.agar.hackathon.agar_epam.app.game.cell.FatJohnyCell
import com.ua.agar.io.hackathon.GameEngine

class AgarApp: Application() {
    override fun onCreate() {
        super.onCreate()
        GameEngine.initialize(FatJohnyCell())
        // GameEngine.initialize(ChaserDanCell())
    }
}
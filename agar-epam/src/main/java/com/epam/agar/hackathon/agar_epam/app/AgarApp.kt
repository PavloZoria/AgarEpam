package com.epam.agar.hackathon.agar_epam.app

import android.app.Application
import com.epam.agar.hackathon.agar_epam.app.game.FatJohnyCell
import com.ua.agar.io.hackathon.playground.GameEngine

class AgarApp: Application() {
    override fun onCreate() {
        super.onCreate()
        GameEngine.initialize(FatJohnyCell())
    }
}
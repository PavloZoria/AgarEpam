package com.epam.agar.hackathon.agar_epam.app

import android.app.Application
import com.epam.agar.hackathon.agar_epam.app.game.TestCell
import com.ua.agar.io.hackathon.playground.GameEngine

class AgarApp: Application() {
    override fun onCreate() {
        super.onCreate()
        GameEngine.initialize(TestCell())
    }
}
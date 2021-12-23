package ua.com.epam.agar.app

import android.app.Application
import ua.com.epam.agar.engine.GameEngine
import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic

abstract class AgarApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GameEngine.initialize(cellImplementation())
        // GameEngine.initialize(ChaserDanCell())
    }

    abstract fun cellImplementation(): CellLogic
}
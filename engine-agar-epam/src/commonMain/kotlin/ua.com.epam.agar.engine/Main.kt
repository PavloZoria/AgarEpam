package ua.com.epam.agar.engine

import ua.com.epam.agar.hackathon.core.entity.main.DesiredCellsState
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.entity.public_api.CellLogic
import ua.com.epam.agar.hackathon.core.printLine
import kotlin.jvm.JvmStatic

//object Main {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        GameEngine.initialize(object : CellLogic() {
//            override fun handleGameUpdate(mapState: MapState): DesiredCellsState? {
//                return null
//            }
//        })
//        GameEngine.startGame("MainRoom")
//        while (true) {
//            printLine("I'm running")
//        }
//    }
//}
package ua.com.epam.agar.hackathon.api.game.socket.game.tick

import ua.com.epam.agar.hackathon.data.game.TickModel

interface GameTickHandler {
    suspend fun sendTick(tick: TickModel)
}
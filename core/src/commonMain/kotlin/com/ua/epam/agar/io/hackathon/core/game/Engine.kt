package com.ua.epam.agar.io.hackathon.core.game

interface Engine {

    suspend fun connectToRoom(roomId: String)

    suspend fun startGame()

    suspend fun configure(roomId: String)

    suspend fun stopGame()
}
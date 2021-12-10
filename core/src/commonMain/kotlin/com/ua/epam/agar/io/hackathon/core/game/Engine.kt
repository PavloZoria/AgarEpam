package com.ua.epam.agar.io.hackathon.core.game

interface Engine {

    suspend fun connectToRoom(roomId: String)

    suspend fun startGame()

    suspend fun configure(roomId: String, isTrainingRoom: Boolean = false)

    suspend fun stopGame()
}
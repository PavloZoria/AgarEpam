package ua.com.epam.agar.hackathon.core.game

interface Engine {

    suspend fun connectToRoom(roomId: String)

    suspend fun startGame()

    suspend fun configure(roomId: String, isTrainingRoom: Boolean = false)

    suspend fun stopGame()
}
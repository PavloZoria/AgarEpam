package com.epam.agar.hackathon.agar_epam.app.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import ua.com.epam.agar.engine.GameEngine

class GameViewModel : ViewModel() {
    private val _roomId: MutableStateFlow<String> = MutableStateFlow("")

    val startGameEnabled: Flow<Boolean> = _roomId.map { it.isNotBlank() }

    fun startGame() {
        GameEngine.startGame(_roomId.value)
    }

    fun stopGame() {
        GameEngine.stopGame()
    }

    fun roomIdChanged(roomId: String) {
        _roomId.value = roomId
    }
}
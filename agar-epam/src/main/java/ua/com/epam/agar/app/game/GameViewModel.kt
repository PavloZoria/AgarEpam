package ua.com.epam.agar.app.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import ua.com.epam.agar.engine.GameEngine
import ua.com.epam.agar.engine.provider.host.clientUrl

class GameViewModel : ViewModel() {
    private val _roomId: MutableStateFlow<String> = MutableStateFlow("")

    val startGameEnabled: Flow<Boolean> = _roomId.map { it.isNotBlank() }
    val gameUrl: Flow<String> = _roomId.map { clientUrl(it) }

    fun startGame() {
        GameEngine.startGame(_roomId.value, true)
    }

    fun stopGame() {
        GameEngine.stopGame()
    }

    fun roomIdChanged(roomId: String) {
        _roomId.value = roomId
    }
}
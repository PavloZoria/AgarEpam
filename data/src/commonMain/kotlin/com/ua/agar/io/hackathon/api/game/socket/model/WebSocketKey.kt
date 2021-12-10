package com.ua.agar.io.hackathon.api.game.socket.model

enum class WebSocketKey(val key: String) {
    GAME_DATA("game_data"),
    GAME_CONFIG("game_config"),
    CONNECT_TO_ROOM("connect_to_room"),
    PLAYER_ACTION("player_action");
}


fun WebSocketModel.isGameData() = this.key == WebSocketKey.GAME_DATA.key
fun WebSocketModel.isConnectToRoom() = this.key == WebSocketKey.CONNECT_TO_ROOM.key
fun WebSocketModel.isPlayerAction() = this.key == WebSocketKey.PLAYER_ACTION.key
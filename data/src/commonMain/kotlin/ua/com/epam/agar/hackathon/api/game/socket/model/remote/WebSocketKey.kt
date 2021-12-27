package ua.com.epam.agar.hackathon.api.game.socket.model.remote

internal enum class WebSocketKey(val key: String) {
    GAME_DATA("game_data"),
    GAME_CONFIG("game_config"),
    CONNECT_TO_ROOM("connect_to_room"),
    PLAYER_ACTION("player_action"),
    DATA_RECEIVED("data_received");
}
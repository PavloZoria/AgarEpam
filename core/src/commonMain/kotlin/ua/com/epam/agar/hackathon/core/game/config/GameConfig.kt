package ua.com.epam.agar.hackathon.core.game.config

/**
 * General game configuration
 */
data class GameConfig(
    val tickTime: Long,
    /**
     * Maximum allowed ticks count. Game will stop after the last one.
     */
    val tickLimit: Long,
    val cellConfig: CellConfig,
    val mapConfig: MapConfig,
    val foodConfig: FoodConfig
)

package ua.com.epam.agar.hackathon.data.cell

import ua.com.epam.agar.hackathon.data.cell.property.PositionModel
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Created class in this way in order to calculate hashcode based
// only on id in order to make work with a HashSet easier
@Serializable
internal data class CellModel(
    @SerialName("id")
    val cellId: String? = null
) {

    @SerialName("player")
    var player: String? = null
        private set

    @SerialName("own")
    var own: Boolean? = null
        private set

    @SerialName("mass")
    var mass: Float? = null
        private set

    @SerialName("radius")
    var radius: Float? = null
        private set

    @SerialName("position")
    var position: PositionModel? = null
        private set

    @SerialName("velocity")
    var velocity: VelocityModel? = null
        private set

    @SerialName("availableEnergy")
    var availableEnergy: Float? = null
        private set

    @SerialName("canSplit")
    var canSplit: Boolean? = null
        private set

    @SerialName("canMerge")
    var canMerge: Boolean? = null
        private set

    @SerialName("speed")
    var speed: Float? = null
        private set

    @SerialName("maxSpeed")
    var maxSpeed: Float? = null
        private set

    @SerialName("eatEfficiency")
    var eatEfficiency: Float? = null
        private set

    @SerialName("del")
    var deleted: Boolean? = null
        private set

    constructor(
        cellId: String?,
        player: String?,
        own: Boolean?,
        mass: Float?,
        radius: Float?,
        position: PositionModel?,
        velocity: VelocityModel?,
        availableEnergy: Float?,
        canSplit: Boolean?,
        canMerge: Boolean?,
        speed: Float?,
        maxSpeed: Float?,
        eatEfficiency: Float?,
        deleted: Boolean?
    ) : this(cellId = cellId) {
        this.player = player
        this.own = own
        this.mass = mass
        this.radius = radius
        this.position = position
        this.velocity = velocity
        this.availableEnergy = availableEnergy
        this.canSplit = canSplit
        this.canMerge = canMerge
        this.speed = speed
        this.maxSpeed = maxSpeed
        this.eatEfficiency = eatEfficiency
        this.deleted = deleted
    }
}
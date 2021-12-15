package ua.com.epam.agar.hackathon.data.cell.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CellActivityModel(
    @SerialName("id")
    val cellId: String,
    @SerialName("speed")
    val cellSpeed: Float = 0f,
    @SerialName("velocity")
    val velocity: VelocityModel? = null,
    @SerialName("growIntention")
    val growIntention: GrowIntentionModel? = null,
    @SerialName("additionalAction")
    val additionalAction: MoveActionModel? = null
)


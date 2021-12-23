package ua.com.epam.agar.hackathon.data.cell.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MoveActionModel(
    @SerialName("split")
    val split: Boolean? = null,
    @SerialName("merge")
    val merge: String? = null
)
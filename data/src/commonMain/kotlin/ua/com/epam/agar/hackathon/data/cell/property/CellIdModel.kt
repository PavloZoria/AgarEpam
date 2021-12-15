package ua.com.epam.agar.hackathon.data.cell.property

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
internal value class CellIdModel(val id: String)

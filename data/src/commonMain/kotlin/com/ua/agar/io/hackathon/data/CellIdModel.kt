package com.ua.agar.io.hackathon.data

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
internal value class CellIdModel(val id: String)

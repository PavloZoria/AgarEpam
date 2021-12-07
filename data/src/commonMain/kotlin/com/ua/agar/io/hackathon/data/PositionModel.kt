package com.ua.agar.io.hackathon.data

import kotlinx.serialization.Serializable

@Serializable
data class PositionModel(val x: Float, val y: Float)

/*
*  "id": String, // the UUID
//         "mass": Float,
//         "energy": Float, // the energy that cell eaten, may be developed into skills or growing the cell
//         "radius": Float,
//         "position": {
//         "x": Float,
//         "y": Float
//     },
//         "velocity": {
//         "x": Float,
//         "y": Float
//     }
* */
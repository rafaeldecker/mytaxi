package com.mytaxy.test.entities

/**
 * Created by Rafael Decker on 2019-05-30.
 */

data class Poi(
    val id: Long,
    val coordinate: Coordinate,
    val fleetType: FleetType,
    val heading: Double
)

package com.mytaxy.test.infra.api.models

import com.mytaxy.test.entities.Coordinate

/**
 * Created by Rafael Decker on 2019-05-30.
 */

data class PoiItem(
    val id: Long,
    val coordinate: Coordinate,
    val fleetType: String,
    val heading: Double
)
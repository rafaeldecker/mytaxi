package com.mytaxi.test.android.screens.map

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Rafael Decker on 2019-06-03.
 */

data class MapModel(
    val id: Long,
    val coordinate: LatLng,
    val carImageResId: Int,
    val heading: Double
)
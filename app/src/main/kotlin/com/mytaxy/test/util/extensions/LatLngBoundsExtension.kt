package com.mytaxy.test.util.extensions

import com.google.android.gms.maps.model.LatLngBounds
import com.mytaxy.test.entities.Bounds

/**
 * Created by Rafael Decker on 2019-06-01.
 */

fun LatLngBounds.toBounds() =
    Bounds(northeast.toCoordinate(), southwest.toCoordinate())

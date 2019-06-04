package com.mytaxi.test.util.extensions

import com.google.android.gms.maps.model.LatLng
import com.mytaxi.test.entities.Coordinate

/**
 * Created by Rafael Decker on 2019-06-01.
 */

fun LatLng.toCoordinate() = Coordinate(latitude, longitude)

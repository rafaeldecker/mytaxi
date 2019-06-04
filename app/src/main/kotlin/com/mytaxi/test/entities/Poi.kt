package com.mytaxi.test.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Rafael Decker on 2019-05-30.
 */

@Parcelize
data class Poi(
    val id: Long,
    val coordinate: Coordinate,
    val fleetType: FleetType,
    val heading: Double
): Parcelable

package com.mytaxy.test.android.screens.home

import com.mytaxy.test.R
import com.mytaxy.test.entities.FleetType
import com.mytaxy.test.entities.Poi
import com.mytaxy.test.util.Mapper
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-30.
 */

class HomeModelMapper @Inject constructor(

) : Mapper<Poi, HomeModelItem> {

    override fun map(param: Poi): HomeModelItem =
        with(param) {
            HomeModelItem(
                id = id,
                titleResId = extractTitle(fleetType),
                backgroundResId = extractBackground(fleetType),
                iconResId = extractIcon(fleetType)
            )
        }

    override fun mapReverse(param: HomeModelItem): Poi {
        throw NotImplementedError("HomeModelMapper mapReverse not implemented")
    }

    private fun extractTitle(fleetType: FleetType): Int =
        when (fleetType) {
            FleetType.TAXI -> R.string.fleet_type_taxi
            FleetType.POOLING -> R.string.fleet_type_pooling
            FleetType.UNKNOWN -> R.string.fleet_type_unknown
        }

    private fun extractBackground(fleetType: FleetType): Int =
        when (fleetType) {
            FleetType.TAXI -> R.drawable.shape_oval_taxi_background
            FleetType.POOLING -> R.drawable.shape_oval_pooling_background
            FleetType.UNKNOWN -> R.drawable.shape_oval_unknown_background
        }

    private fun extractIcon(fleetType: FleetType): Int =
        when (fleetType) {
            FleetType.TAXI -> R.drawable.ic_taxi
            FleetType.POOLING -> R.drawable.ic_pooling
            FleetType.UNKNOWN -> R.drawable.ic_unknown
        }

}

package com.mytaxi.test.android.screens.map

import com.mytaxi.test.R
import com.mytaxi.test.entities.FleetType
import com.mytaxi.test.entities.Poi
import com.mytaxi.test.util.Mapper
import com.mytaxi.test.util.extensions.toLatLong
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-06-03.
 */

class MapModelMapper @Inject constructor(

) : Mapper<Poi, MapModel> {

    override fun map(param: Poi): MapModel =
        with(param) {
            MapModel(
                id = id,
                coordinate = coordinate.toLatLong(),
                heading = heading,
                carImageResId = carImage(fleetType)
            )
        }

    override fun mapReverse(param: MapModel): Poi {
        throw NotImplementedError("MapModelMapper mapReverse not implemented")
    }

    private fun carImage(type: FleetType) =
        when (type) {
            FleetType.POOLING -> R.drawable.ic_map_pooling
            else -> R.drawable.ic_map_taxi
        }

}

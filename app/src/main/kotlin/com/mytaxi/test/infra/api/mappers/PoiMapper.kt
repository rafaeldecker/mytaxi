package com.mytaxi.test.infra.api.mappers

import com.mytaxi.test.entities.FleetType
import com.mytaxi.test.entities.Poi
import com.mytaxi.test.infra.api.models.PoiItem
import com.mytaxi.test.util.Mapper
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-30.
 */

class PoiMapper @Inject constructor(
    private val fleetTypeMapper: Mapper<String, FleetType>
) : Mapper<PoiItem, Poi> {

    override fun map(param: PoiItem): Poi =
        with(param) {
            Poi(
                id = id,
                coordinate = coordinate,
                fleetType = fleetTypeMapper.map(fleetType),
                heading = heading
            )
        }

    override fun mapReverse(param: Poi): PoiItem =
        throw NotImplementedError("PoiMapper mapReverse not implemented")

}

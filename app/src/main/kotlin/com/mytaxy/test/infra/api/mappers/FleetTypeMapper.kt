package com.mytaxy.test.infra.api.mappers

import com.mytaxy.test.entities.FleetType
import com.mytaxy.test.util.Mapper
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-30.
 */

class FleetTypeMapper @Inject constructor(

) : Mapper<String, FleetType> {

    override fun map(param: String): FleetType =
        when (param.toLowerCase()) {
            "taxi" -> FleetType.TAXI
            "pooling" -> FleetType.POOLING
            else -> FleetType.UNKNOWN
        }

    override fun mapReverse(param: FleetType): String {
        throw NotImplementedError("FleetTypeMapper mapReverse not implemented")
    }

}

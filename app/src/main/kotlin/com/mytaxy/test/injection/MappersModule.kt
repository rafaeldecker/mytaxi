package com.mytaxy.test.injection

import com.mytaxy.test.entities.FleetType
import com.mytaxy.test.entities.Poi
import com.mytaxy.test.infra.api.mappers.FleetTypeMapper
import com.mytaxy.test.infra.api.mappers.PoiMapper
import com.mytaxy.test.infra.api.models.PoiItem
import com.mytaxy.test.util.Mapper
import dagger.Binds
import dagger.Module

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Module
abstract class MappersModule {

    @Binds
    abstract fun bindFleetTypeMapper(
        mapper: FleetTypeMapper
    ): Mapper<String, FleetType>

    @Binds
    abstract fun bindPoiMapper(
        mapper: PoiMapper
    ): Mapper<PoiItem, Poi>

}

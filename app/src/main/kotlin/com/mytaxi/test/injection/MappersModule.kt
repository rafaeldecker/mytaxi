package com.mytaxi.test.injection

import com.mytaxi.test.android.screens.home.HomeModelItem
import com.mytaxi.test.android.screens.home.HomeModelMapper
import com.mytaxi.test.android.screens.map.MapModel
import com.mytaxi.test.android.screens.map.MapModelMapper
import com.mytaxi.test.entities.FleetType
import com.mytaxi.test.entities.Poi
import com.mytaxi.test.infra.api.mappers.FleetTypeMapper
import com.mytaxi.test.infra.api.mappers.PoiMapper
import com.mytaxi.test.infra.api.models.PoiItem
import com.mytaxi.test.util.Mapper
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

    @Binds
    abstract fun bindHomeModelMapper(
        mapper: HomeModelMapper
    ): Mapper<Poi, HomeModelItem>

    @Binds
    abstract fun bindMapModelMapper(
        mapper: MapModelMapper
    ): Mapper<Poi, MapModel>

}

package com.mytaxy.test.infra.api.mappers

import com.mytaxy.test.entities.Coordinate
import com.mytaxy.test.entities.FleetType
import com.mytaxy.test.entities.Poi
import com.mytaxy.test.infra.api.models.PoiItem
import com.mytaxy.test.util.Mapper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.AssertionError

/**
 * Created by Rafael Decker on 2019-06-02.
 */

class PoiMapperTest {

    private lateinit var mapper: Mapper<PoiItem, Poi>

    @Before
    fun setup() {
        mapper = PoiMapper(FleetTypeMapper())
    }

    @Test
    fun testMapping() {
        val coordinate = Coordinate(
            latitude = 53.694865,
            longitude = 9.757589
        )
        val heading = 188.0
        val fleetType = "taxi"
        val poiItem = PoiItem(
            id = 1L,
            coordinate = coordinate,
            heading = heading,
            fleetType = fleetType
        )
        val poi = mapper.map(poiItem)
        Assert.assertEquals(poiItem.id, poi.id)
        Assert.assertTrue(poiItem.coordinate.latitude == poi.coordinate.latitude)
        Assert.assertTrue(poiItem.coordinate.longitude == poi.coordinate.longitude)
        Assert.assertTrue(poiItem.heading == poi.heading)
        Assert.assertTrue(FleetType.TAXI == poi.fleetType)
    }

    @Test(expected = NotImplementedError::class)
    fun testMappingReverseIsNotImplemented() {
        val poi = Poi(0L, Coordinate(0.0, 0.0),FleetType.POOLING, 13.0)
        mapper.mapReverse(poi)
    }

}



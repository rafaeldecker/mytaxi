package com.mytaxy.test.infra.api.mappers

import com.mytaxy.test.entities.FleetType
import com.mytaxy.test.util.Mapper
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Decker on 2019-06-02.
 */

class FleetTypeMapperTest {

    private lateinit var mapper: Mapper<String, FleetType>

    @Before
    fun setup() {
        mapper = FleetTypeMapper()
    }

    @Test
    fun testMappingTaxi() {
        var type = mapper.map("Taxi")
        Assert.assertEquals(type, FleetType.TAXI)
        type = mapper.map("taxi")
        Assert.assertEquals(type, FleetType.TAXI)
    }

    @Test
    fun testMappingPoollig() {
        var type = mapper.map("Pooling")
        Assert.assertEquals(type, FleetType.POOLING)
        type = mapper.map("pooling")
        Assert.assertEquals(type, FleetType.POOLING)
    }

    @Test
    fun testMappingUnknown() {
        val type = mapper.map("taxxi")
        Assert.assertEquals(type, FleetType.UNKNOWN)
    }

    @Test(expected = NotImplementedError::class)
    fun testMappingReverseIsNotImplemented() {
        mapper.mapReverse(FleetType.POOLING)
    }

}

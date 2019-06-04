package com.mytaxi.test.domain

import com.mytaxi.test.entities.Bounds
import com.mytaxi.test.entities.Coordinate
import com.mytaxi.test.entities.Poi
import com.mytaxi.test.infra.api.PoiApi
import com.mytaxi.test.infra.api.models.PoiItem
import com.mytaxi.test.infra.api.models.PoiResponse
import com.mytaxi.test.util.Mapper
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times

/**
 * Created by Rafael Decker on 2019-06-03.
 */

class FetchPoiUseCaseTest {

    @Mock
    lateinit var apiMock: PoiApi

    @Mock
    lateinit var mapper: Mapper<PoiItem, Poi>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test api and mapper is called only once with right parameters`() {
        val bounds = Bounds(
            firstPoint = Coordinate(
                latitude = 53.694865,
                longitude = 9.757589
            ),
            secondPoint = Coordinate(
                latitude = 53.394655,
                longitude = 10.099891
            )
        )
        val mockResponse = PoiResponse(emptyList())
        Mockito.`when`(
            apiMock.fetch(
                bounds.firstPoint.latitude,
                bounds.firstPoint.longitude,
                bounds.secondPoint.latitude,
                bounds.secondPoint.longitude
            )
        ).thenReturn(Observable.just(mockResponse))
        Mockito.`when`(mapper.mapList(mockResponse.poiList)).thenReturn(emptyList())

        val useCase = FetchPoiUseCaseImpl(apiMock, mapper)
        useCase.fetch(bounds).test()
        Mockito.verify(apiMock, Times(1))
            .fetch(
                bounds.firstPoint.latitude,
                bounds.firstPoint.longitude,
                bounds.secondPoint.latitude,
                bounds.secondPoint.longitude
            )

        Mockito.verify(mapper, Times(1))
            .mapList(mockResponse.poiList)
    }

}

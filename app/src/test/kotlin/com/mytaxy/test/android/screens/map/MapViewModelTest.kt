package com.mytaxy.test.android.screens.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mytaxy.test.android.screens.base.ViewModelState
import com.mytaxy.test.domain.FetchPoiUseCase
import com.mytaxy.test.entities.*
import com.mytaxy.test.infra.schedulers.TrampolineRxSchedulerProvider
import com.mytaxy.test.util.Mapper
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Rafael Decker on 2019-06-02.
 */

class MapViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var fetchPoiUseCase: FetchPoiUseCase

    @Mock
    lateinit var mapper: Mapper<Poi, MapModel>

    private val schedulerProvider = TrampolineRxSchedulerProvider()

    lateinit var viewModel: MapViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MapViewModel(
            fetchPoiUseCase,
            mapper,
            schedulerProvider
        )
    }

    @Test
    fun `on successful fetch is updating state to data`() {
        Mockito.`when`(fetchPoiUseCase.fetch(mockCity.bounds)).thenReturn(Observable.just(mockPoiList))
        viewModel.onBoundsChanged(mockCity.bounds)
        Assert.assertTrue(viewModel.state.value is ViewModelState.Data<*>)
    }

    @Test
    fun `on fetch fails is updating state to error`() {
        Mockito.`when`(fetchPoiUseCase.fetch(mockCity.bounds)).thenReturn(Observable.error(Throwable()))
        viewModel.onBoundsChanged(mockCity.bounds)
        Assert.assertTrue(viewModel.state.value is ViewModelState.Error)
    }

    @Test
    fun `on starting with selected poi is adding it in the data list`() {
        Mockito.`when`(fetchPoiUseCase.fetch(mockCity.bounds)).thenReturn(Observable.just(mockPoiList))
        viewModel = MapViewModel(
            fetchPoiUseCase,
            MapModelMapper(),
            schedulerProvider
        )
        viewModel.onMapReady(selectedPoi)
        viewModel.onBoundsChanged(mockCity.bounds)
        Assert.assertTrue(viewModel.state.value is ViewModelState.Data<*>)
        val data = (viewModel.state.value as ViewModelState.Data<*>).data as List<MapModel>
        Assert.assertTrue(data.size == 2)
        Assert.assertTrue(data.any { it.id == selectedPoi.id })
    }

    @Test
    fun `on starting with no selected poi is not adding it in the data list`() {
        Mockito.`when`(fetchPoiUseCase.fetch(mockCity.bounds)).thenReturn(Observable.just(mockPoiList))
        viewModel = MapViewModel(
            fetchPoiUseCase,
            MapModelMapper(),
            schedulerProvider
        )
        viewModel.onMapReady(null)
        viewModel.onBoundsChanged(mockCity.bounds)
        Assert.assertTrue(viewModel.state.value is ViewModelState.Data<*>)
        val data = (viewModel.state.value as ViewModelState.Data<*>).data as List<MapModel>
        Assert.assertTrue(data.size == 1)
    }

    companion object {
        private val mockCity = City(
            name = "Test",
            bounds = Bounds(
                firstPoint = Coordinate(
                    latitude = 53.694865,
                    longitude = 9.757589
                ),
                secondPoint = Coordinate(
                    latitude = 53.394655,
                    longitude = 10.099891
                )
            )
        )

        private val mockPoiList = listOf(
                Poi(
                    1,
                    Coordinate(
                        latitude = 53.694865,
                        longitude = 9.757589
                    ),
                    fleetType = FleetType.POOLING,
                    heading = 190.0
                )
            )

        private val selectedPoi = Poi(
            2,
            Coordinate(
                latitude = 53.694865,
                longitude = 9.757589
            ),
            fleetType = FleetType.POOLING,
            heading = 190.0
        )

    }

}
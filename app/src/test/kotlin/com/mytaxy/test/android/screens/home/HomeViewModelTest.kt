package com.mytaxy.test.android.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mytaxy.test.android.screens.base.ViewModelNavigator
import com.mytaxy.test.android.screens.base.ViewModelState
import com.mytaxy.test.domain.FetchPoiUseCase
import com.mytaxy.test.domain.GetCurrentCityUseCase
import com.mytaxy.test.entities.*
import com.mytaxy.test.infra.logger.Logger
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

class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var logger: Logger

    @Mock
    lateinit var getCurrentCityUseCase: GetCurrentCityUseCase

    @Mock
    lateinit var getPoiUseCase: FetchPoiUseCase

    @Mock
    lateinit var homeItemModelMapper: Mapper<Poi, HomeModelItem>

    private val schedulerProvider = TrampolineRxSchedulerProvider()

    lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(
            logger,
            getCurrentCityUseCase,
            getPoiUseCase,
            homeItemModelMapper,
            schedulerProvider
        )
    }

    @Test
    fun `on successful fetch is updating state to data`() {
        val city = mockCity
        Mockito.`when`(getCurrentCityUseCase.getCity()).thenReturn(Observable.just(city))
        Mockito.`when`(getPoiUseCase.fetch(city.bounds)).thenReturn(Observable.just(mockPoiList))
        viewModel.fetchData()
        Assert.assertTrue(viewModel.state.value is ViewModelState.Data<*>)
    }

    @Test
    fun `on fetch fails is updating state to error`() {
        Mockito.`when`(getCurrentCityUseCase.getCity()).thenReturn(Observable.error(Throwable()))
        viewModel = HomeViewModel(
            logger,
            getCurrentCityUseCase,
            getPoiUseCase,
            homeItemModelMapper,
            schedulerProvider
        )
        viewModel.fetchData()
        Assert.assertTrue(viewModel.state.value is ViewModelState.Error)
    }

    @Test
    fun `on item clicked is updating navigator`() {
        val city = mockCity
        Mockito.`when`(getCurrentCityUseCase.getCity()).thenReturn(Observable.just(city))
        Mockito.`when`(getPoiUseCase.fetch(city.bounds)).thenReturn(Observable.just(mockPoiList))
        Mockito.`when`(homeItemModelMapper.mapList(mockPoiList)).thenReturn(listOf(mockModelItem))
        viewModel.fetchData()
        viewModel.onItemClicked(mockModelItem)
        viewModel.navigator.observeForever {
            Assert.assertTrue(it is ViewModelNavigator.Navigate<*>)
            Assert.assertTrue((it as ViewModelNavigator.Navigate<*>).target is Poi)
            Assert.assertTrue((it.target as Poi).id == mockPoiList.first().id)
        }
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

        private val mockModelItem = mockPoiList.map {
            HomeModelItem(it.id,0,0,0)
        }.first()
    }

}
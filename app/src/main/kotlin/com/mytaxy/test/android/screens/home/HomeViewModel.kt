package com.mytaxy.test.android.screens.home

import com.mytaxy.test.android.screens.base.MvvmViewModel
import com.mytaxy.test.android.screens.base.ViewModelState
import com.mytaxy.test.domain.FetchPoiUseCase
import com.mytaxy.test.domain.GetCurrentCityUseCase
import com.mytaxy.test.entities.Poi
import com.mytaxy.test.infra.logger.Logger
import com.mytaxy.test.infra.schedulers.RxSchedulerProvider
import com.mytaxy.test.util.Mapper
import com.mytaxy.test.util.extensions.observeOnMainThread
import com.mytaxy.test.util.extensions.subscribeOnIo
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-28.
 */

class HomeViewModel @Inject constructor(
    private val logger: Logger,
    private val getCurrentCityUseCase: GetCurrentCityUseCase,
    private val getPoiUseCase: FetchPoiUseCase,
    private val homeItemModelMapper: Mapper<Poi, HomeModelItem>,
    private val rxSchedulerProvider: RxSchedulerProvider
) : MvvmViewModel() {

    fun fetchData() {
        addDisposable(
            getCurrentCityUseCase.getCity()
                .doOnSubscribe { showLoading() }
                .subscribeOnIo(rxSchedulerProvider)
                .flatMap { city ->
                    getPoiUseCase.fetch(city.bounds)
                        .map { homeItemModelMapper.mapList(it) }
                        .map { city to it }
                }
                .observeOnMainThread(rxSchedulerProvider)
                .subscribe({
                    val city = it.first
                    val poiList = it.second
                    updateState(
                        ViewModelState.Data(
                            HomeModel(
                                title = city.name,
                                items = poiList
                            )
                        )
                    )
                }, {
                    logger.e(it)
                    handleError(it)
                })
        )
    }

}
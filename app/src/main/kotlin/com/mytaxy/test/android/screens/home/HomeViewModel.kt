package com.mytaxy.test.android.screens.home

import com.mytaxy.test.android.screens.base.MvvmViewModel
import com.mytaxy.test.android.screens.base.ViewModelState
import com.mytaxy.test.domain.FetchPoiUseCase
import com.mytaxy.test.domain.GetCurrentCityUseCase
import com.mytaxy.test.infra.logger.Logger
import com.mytaxy.test.infra.schedulers.RxSchedulerProvider
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
    private val rxSchedulerProvider: RxSchedulerProvider
): MvvmViewModel() {

    fun fetchData() {
        addDisposable(
            getCurrentCityUseCase.getCity()
                .doOnSubscribe { showLoading() }
                .subscribeOnIo(rxSchedulerProvider)
                .flatMap { getPoiUseCase.fetch(it.bounds) }
                .observeOnMainThread(rxSchedulerProvider)
                .subscribe({
                    logger.d(it.toString())
                    updateState(ViewModelState.Data(it))
                },{
                    handleError(it)
                })
        )
    }

}
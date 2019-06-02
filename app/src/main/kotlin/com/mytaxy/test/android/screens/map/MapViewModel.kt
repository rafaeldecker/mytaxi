package com.mytaxy.test.android.screens.map

import com.google.android.gms.maps.model.LatLngBounds
import com.mytaxy.test.android.screens.base.MvvmViewModel
import com.mytaxy.test.android.screens.base.ViewModelState
import com.mytaxy.test.domain.FetchPoiUseCase
import com.mytaxy.test.entities.Bounds
import com.mytaxy.test.entities.Poi
import com.mytaxy.test.infra.schedulers.RxSchedulerProvider
import com.mytaxy.test.util.extensions.saveMainThread
import com.mytaxy.test.util.extensions.toBounds
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-06-01.
 */

class MapViewModel @Inject constructor(
    private val fetchPoiUseCase: FetchPoiUseCase,
    private val rxSchedulerProvider: RxSchedulerProvider
): MvvmViewModel() {

    private var selectedPoi: Poi? = null

    fun updateSelectedPoi(poi: Poi?) {
        selectedPoi = poi
    }

    fun onBoundsChanged(bound: LatLngBounds) {
        fetchData(bound.toBounds())
    }

    private fun fetchData(bounds: Bounds) {
        addDisposable(
            fetchPoiUseCase.fetch(bounds)
                .saveMainThread(rxSchedulerProvider)
                .subscribe({
                    updateState(ViewModelState.Data(it))
                },{
                    handleError(it)
                })
        )
    }

}
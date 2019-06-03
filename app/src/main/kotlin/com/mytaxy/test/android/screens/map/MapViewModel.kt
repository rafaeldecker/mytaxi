package com.mytaxy.test.android.screens.map

import com.google.android.gms.maps.model.LatLngBounds
import com.mytaxy.test.android.screens.base.MvvmViewModel
import com.mytaxy.test.android.screens.base.ViewModelState
import com.mytaxy.test.domain.FetchPoiUseCase
import com.mytaxy.test.entities.Bounds
import com.mytaxy.test.entities.Poi
import com.mytaxy.test.infra.schedulers.RxSchedulerProvider
import com.mytaxy.test.util.Mapper
import com.mytaxy.test.util.extensions.saveMainThread
import com.mytaxy.test.util.extensions.toBounds
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-06-01.
 */

class MapViewModel @Inject constructor(
    private val fetchPoiUseCase: FetchPoiUseCase,
    private val mapper: Mapper<Poi, MapModel>,
    private val rxSchedulerProvider: RxSchedulerProvider
): MvvmViewModel() {

    private var selectedPoi: Poi? = null

    init {
        showLoading()
    }

    fun onBoundsChanged(bounds: Bounds) {
        fetchData(bounds)
    }

    fun onMapReady(poi: Poi?) {
        selectedPoi = poi
    }

    private fun fetchData(bounds: Bounds) {
        addDisposable(
            fetchPoiUseCase.fetch(bounds)
                .doOnSubscribe { showLoading() }
                .map {
                    val data = addSelectedIfNeeded(it)
                    mapper.mapList(data)
                }
                .saveMainThread(rxSchedulerProvider)
                .subscribe({
                    updateState(ViewModelState.Data(it))
                },{
                    handleError(it)
                })
        )
    }

    private fun addSelectedIfNeeded(list: List<Poi>): List<Poi> {
        selectedPoi?.let { selected ->
            val mutable = list.toMutableList()
            mutable.add(0, selected)
            return mutable
        }
        return list
    }

}

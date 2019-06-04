package com.mytaxi.test.android.screens.map

import com.mytaxi.test.android.screens.base.MvvmViewModel
import com.mytaxi.test.android.screens.base.ViewModelState
import com.mytaxi.test.domain.FetchPoiUseCase
import com.mytaxi.test.entities.Bounds
import com.mytaxi.test.entities.Poi
import com.mytaxi.test.infra.schedulers.RxSchedulerProvider
import com.mytaxi.test.util.Mapper
import com.mytaxi.test.util.extensions.saveMainThread
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

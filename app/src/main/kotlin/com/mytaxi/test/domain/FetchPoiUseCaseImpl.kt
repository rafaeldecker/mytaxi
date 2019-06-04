package com.mytaxi.test.domain

import com.mytaxi.test.entities.Bounds
import com.mytaxi.test.entities.Poi
import com.mytaxi.test.infra.api.PoiApi
import com.mytaxi.test.infra.api.models.PoiItem
import com.mytaxi.test.util.Mapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-30.
 */

class FetchPoiUseCaseImpl @Inject constructor(
    private val api: PoiApi,
    private val mapper: Mapper<PoiItem, Poi>
): FetchPoiUseCase {

    override fun fetch(bounds: Bounds): Observable<List<Poi>> =
            api.fetch(
                bounds.firstPoint.latitude,
                bounds.firstPoint.longitude,
                bounds.secondPoint.latitude,
                bounds.secondPoint.longitude
            ).map {
                mapper.mapList(it.poiList)
            }

}

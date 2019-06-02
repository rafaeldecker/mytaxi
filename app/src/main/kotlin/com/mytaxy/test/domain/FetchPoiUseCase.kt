package com.mytaxy.test.domain

import com.mytaxy.test.entities.Bounds
import com.mytaxy.test.entities.Poi
import io.reactivex.Observable

/**
 * Use case responsible by fetching vehicle information
 * on a desired bounds
 *
 * Created by Rafael Decker on 2019-05-30.
 */

interface FetchPoiUseCase {

    /**
     * Fetches vehicles from desired bounds
     * @param bounds desired bound
     * @return list of POI
     */
    fun fetch(bounds: Bounds): Observable<List<Poi>>

}

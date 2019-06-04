package com.mytaxi.test.domain

import com.mytaxi.test.entities.Bounds
import com.mytaxi.test.entities.Poi
import io.reactivex.Observable

/**
 * Created by Rafael Decker on 2019-05-30.
 */

interface FetchPoiUseCase {

    fun fetch(bounds: Bounds): Observable<List<Poi>>

}

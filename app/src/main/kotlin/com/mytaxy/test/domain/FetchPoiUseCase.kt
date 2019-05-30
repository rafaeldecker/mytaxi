package com.mytaxy.test.domain

import com.mytaxy.test.entities.Bounds
import com.mytaxy.test.entities.Poi
import io.reactivex.Observable

/**
 * Created by Rafael Decker on 2019-05-30.
 */

interface FetchPoiUseCase {

    fun fetch(bounds: Bounds): Observable<List<Poi>>

}

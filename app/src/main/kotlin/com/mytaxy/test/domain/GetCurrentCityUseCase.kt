package com.mytaxy.test.domain

import com.mytaxy.test.entities.City
import io.reactivex.Observable

/**
 * Created by Rafael Decker on 2019-05-30.
 */

interface GetCurrentCityUseCase {

    fun getCity(): Observable<City>

}
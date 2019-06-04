package com.mytaxi.test.domain

import com.mytaxi.test.entities.City
import io.reactivex.Observable

/**
 * Created by Rafael Decker on 2019-05-30.
 */

interface GetCurrentCityUseCase {

    fun getCity(): Observable<City>

}
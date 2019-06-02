package com.mytaxy.test.domain

import com.mytaxy.test.entities.City
import io.reactivex.Observable

/**
 * Use case responsible by delivering current city.
 *
 * Created by Rafael Decker on 2019-05-30.
 */

interface GetCurrentCityUseCase {

    /**
     * Returns the current city
     *
     * @return city observable
     */
    fun getCity(): Observable<City>

}
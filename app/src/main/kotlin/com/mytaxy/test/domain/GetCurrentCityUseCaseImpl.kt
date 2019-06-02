package com.mytaxy.test.domain

import com.mytaxy.test.entities.Bounds
import com.mytaxy.test.entities.City
import com.mytaxy.test.entities.Coordinate
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-30.
 */

class GetCurrentCityUseCaseImpl @Inject constructor(

) : GetCurrentCityUseCase {

    // TODO: Currently hardcoded, update it in the future to use geolocation/another approach if needed

    override fun getCity(): Observable<City> =
        Observable.just(
            City(
                name = "Hamburg",
                bounds = Bounds(
                    firstPoint = Coordinate(
                        latitude = 53.694865,
                        longitude = 9.757589
                    ),
                    secondPoint = Coordinate(
                        latitude = 53.394655,
                        longitude = 10.099891
                    )
                )
            )
        )

}

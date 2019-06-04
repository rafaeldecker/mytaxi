package com.mytaxi.test.infra.api

import com.mytaxi.test.infra.api.models.PoiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Rafael Decker on 2019-05-30.
 */

interface PoiApi {

    @GET(".")
    fun fetch(
        @Query("p1Lat") firstPointLat: Double,
        @Query("p1Lon") firstPointLon: Double,
        @Query("p2Lat") secondPointLat: Double,
        @Query ("p2Lon") secondPointLon: Double
    ): Observable<PoiResponse>

}

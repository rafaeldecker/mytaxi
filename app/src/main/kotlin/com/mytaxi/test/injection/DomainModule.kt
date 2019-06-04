package com.mytaxi.test.injection

import com.mytaxi.test.domain.FetchPoiUseCase
import com.mytaxi.test.domain.FetchPoiUseCaseImpl
import com.mytaxi.test.domain.GetCurrentCityUseCase
import com.mytaxi.test.domain.GetCurrentCityUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindGetCurrentCityUseCase(
        useCase: GetCurrentCityUseCaseImpl
    ): GetCurrentCityUseCase

    @Binds
    abstract fun bindFetchPoiUseCase(
        useCase: FetchPoiUseCaseImpl
    ): FetchPoiUseCase

}
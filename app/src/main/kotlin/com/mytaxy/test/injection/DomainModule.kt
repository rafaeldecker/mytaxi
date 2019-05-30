package com.mytaxy.test.injection

import com.mytaxy.test.domain.FetchPoiUseCase
import com.mytaxy.test.domain.FetchPoiUseCaseImpl
import com.mytaxy.test.domain.GetCurrentCityUseCase
import com.mytaxy.test.domain.GetCurrentCityUseCaseImpl
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
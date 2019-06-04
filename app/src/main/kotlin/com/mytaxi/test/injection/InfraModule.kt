package com.mytaxi.test.injection

import com.mytaxi.test.infra.logger.Logger
import com.mytaxi.test.infra.logger.TimberLogger
import com.mytaxi.test.infra.schedulers.RxSchedulerProvider
import com.mytaxi.test.infra.schedulers.RxSchedulerProviderImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Module
class InfraModule {

    @Provides
    fun providesLogger(
        logger: TimberLogger
    ): Logger = logger

    @Provides
    fun providesSchedulers(
        schedulers: RxSchedulerProviderImpl
    ): RxSchedulerProvider = schedulers

}

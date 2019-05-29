package com.mytaxy.test.injection

import com.mytaxy.test.infra.logger.Logger
import com.mytaxy.test.infra.logger.TimberLogger
import com.mytaxy.test.infra.schedulers.RxSchedulerProvider
import com.mytaxy.test.infra.schedulers.RxSchedulerProviderImpl
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

package com.mytaxi.test.android

import android.app.Application
import com.mytaxi.test.infra.logger.Logger
import com.mytaxi.test.injection.ApplicationComponent
import com.mytaxi.test.injection.DaggerApplicationComponent
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-28.
 */

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()
        startServices()
    }

    private fun startServices() {
        startDagger()
        startLogger()
    }

    private fun startDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        applicationComponent.inject(this)
    }

    private fun startLogger() {
        logger.start()
    }

}
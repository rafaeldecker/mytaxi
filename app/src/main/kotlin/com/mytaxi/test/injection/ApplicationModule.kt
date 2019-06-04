package com.mytaxi.test.injection

import android.app.Application
import android.content.Context
import com.mytaxi.test.android.App
import dagger.Module
import dagger.Provides

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Module
class ApplicationModule {

    @Provides
    fun provideContext(app: App): Context = app

    @Provides
    fun provideApplication(app: App): Application = app

}

package com.mytaxy.test.injection

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Module
class ActivityModule(
    private val activity: AppCompatActivity
) {

    @Provides
    @ActivityScope
    fun provideAppCompatActivity(): AppCompatActivity = activity

}

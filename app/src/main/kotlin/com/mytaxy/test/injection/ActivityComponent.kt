package com.mytaxy.test.injection

import androidx.appcompat.app.AppCompatActivity
import com.mytaxy.test.android.App
import com.mytaxy.test.android.screens.home.MainActivity
import dagger.Subcomponent

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@ActivityScope
@Subcomponent(modules = [
    ActivityModule::class
])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    companion object {

        fun init(activity: AppCompatActivity): ActivityComponent {
            val applicationComponent = (activity.application as App).applicationComponent
            return applicationComponent.plus(ActivityModule(activity))
        }

    }

}
package com.mytaxy.test.injection

import com.mytaxy.test.android.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiModule::class,
        DomainModule::class,
        InfraModule::class,
        MappersModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(app: App): Builder
    }

    fun inject(application: App)

    fun plus(activityModule: ActivityModule): ActivityComponent

}

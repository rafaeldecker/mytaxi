package com.mytaxi.test.injection

import androidx.lifecycle.ViewModel
import com.mytaxi.test.android.screens.home.HomeViewModel
import com.mytaxi.test.android.screens.map.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(vm: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(vm: MapViewModel): ViewModel

}
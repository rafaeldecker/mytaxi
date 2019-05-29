package com.mytaxy.test.android.screens.base

/**
 * Created by Rafael Decker on 2019-05-28.
 */

sealed class ViewModelState {

    data class Data<T>(val data: T) : ViewModelState()
    data class Error(val throwable: Throwable) : ViewModelState()
    object Loading : ViewModelState()

}

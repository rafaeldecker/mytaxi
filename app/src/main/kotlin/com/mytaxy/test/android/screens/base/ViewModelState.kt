package com.mytaxy.test.android.screens.base

/**
 * Represents view model state
 *
 * Created by Rafael Decker on 2019-05-28.
 */

sealed class ViewModelState {

    /**
     * Data that should be displayed by the view
     *
     * @param T type of data to be displayed
     */
    data class Data<T>(val data: T) : ViewModelState()

    /**
     * Represents the error state
     *
     * @property throwable to help view displaying error
     */
    data class Error(val throwable: Throwable) : ViewModelState()

    /**
     * Loading state
     */
    object Loading : ViewModelState()

}

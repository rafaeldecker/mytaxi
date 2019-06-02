package com.mytaxy.test.android.screens.base

/**
 * Class that represents a View Model Navigator
 *
 * Created by Rafael Decker on 2019-05-28.
 */

sealed class ViewModelNavigator {

    /**
     * @param T navigation target (transport useful information)
     */
    data class Navigate<T>(val target: T): ViewModelNavigator()

}

fun withNavigator(navigator: Any, block: (Any) -> (Unit)) {
    if (navigator is ViewModelNavigator.Navigate<*> && navigator.target != null) {
        block(navigator.target)
    }
}

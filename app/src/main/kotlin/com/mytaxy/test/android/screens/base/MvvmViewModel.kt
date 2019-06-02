package com.mytaxy.test.android.screens.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytaxy.test.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base class for every View Model
 *
 * Created by Rafael Decker on 2019-05-28.
 */

abstract class MvvmViewModel : ViewModel() {

    /**
     * Composite disposable that should be cleared on view model destroyed
     */
    private val disposables = CompositeDisposable()

    /**
     * Current view model state
     */
    private val _state = MutableLiveData<ViewModelState>()

    /**
     * Navigator
     */
    private val _navigator = SingleLiveEvent<ViewModelNavigator>()

    /**
     * Exposes not mutable current view model state
     */
    val state: LiveData<ViewModelState> by lazy { _state }

    /**
     * Exposes not mutable view model navigator
     */
    val navigator: LiveData<ViewModelNavigator> by lazy { _navigator }

    /**
     * Adds disposable to composite disposable
     *
     * @param disposable
     */
    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    /**
     * Disposes
     */
    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    /**
     * Updates the current view model state
     *
     * @param state desired
     */
    protected open fun updateState(state: ViewModelState) {
        _state.postValue(state)
    }

    /**
     * Updates view model navigator
     *
     * @param navigator data
     */
    protected fun updateNavigator(navigator: ViewModelNavigator) {
        _navigator.postValue(navigator)
    }

    /**
     * Updates current view model state to ViewModelState.Error
     *
     * @param error
     */
    protected fun handleError(error: Throwable) {
        updateState(ViewModelState.Error(error))
    }

    /**
     * Updates current view model state to ViewModelState.Loading
     */
    protected open fun showLoading() {
        updateState(ViewModelState.Loading)
    }

}

package com.mytaxi.test.android.screens.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytaxi.test.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Rafael Decker on 2019-05-28.
 */

abstract class MvvmViewModel: ViewModel() {

    private val disposables = CompositeDisposable()

    private val _state = MutableLiveData<ViewModelState>()

    private val _navigator = SingleLiveEvent<ViewModelNavigator>()

    val state: LiveData<ViewModelState> by lazy { _state }

    val navigator: LiveData<ViewModelNavigator> by lazy { _navigator }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    protected open fun updateState(state: ViewModelState) {
        _state.postValue(state)
    }

    protected fun updateNavigator(navigator: ViewModelNavigator) {
        _navigator.postValue(navigator)
    }

    protected fun handleError(error: Throwable) {
        updateState(ViewModelState.Error(error))
    }

    protected open fun showLoading() {
        updateState(ViewModelState.Loading)
    }

}

package com.mytaxy.test.android.screens.base

import android.os.Bundle
import com.mytaxy.test.android.App
import com.mytaxy.test.injection.ViewModelFactory
import com.mytaxy.test.util.extensions.observe
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-28.
 */

abstract class MvvmActivity<T : MvvmViewModel> : BaseActivity() {

    protected val injector
        get() = (application as App).applicationComponent

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        assignDependencies()
        super.onCreate(savedInstanceState)
        observeViewModelState()
        observeViewModelNavigator()
    }

    protected abstract fun assignDependencies()

    protected open fun observeViewModelState() {
        observe(viewModel.state, ::onViewModelStateChanged)
    }

    protected open fun observeViewModelNavigator() {
        observe(viewModel.navigator, ::onNavigate)
    }

    protected open fun onViewModelStateChanged(state: ViewModelState) {

    }

    protected open fun onNavigate(navigator: ViewModelNavigator) {

    }

}

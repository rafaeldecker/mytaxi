package com.mytaxy.test.android.screens.base

import android.os.Bundle
import com.mytaxy.test.android.App
import com.mytaxy.test.injection.ViewModelFactory
import com.mytaxy.test.util.extensions.observe
import javax.inject.Inject

/**
 * Base class for every activity that will
 * implement MVVM architecture
 *
 * Created by Rafael Decker on 2019-05-28.
 */

abstract class MvvmActivity<T : MvvmViewModel> : BaseActivity() {

    protected val injector
        get() = (application as App).applicationComponent

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    /**
     * View model instance
     */
    abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        assignDependencies()
        super.onCreate(savedInstanceState)
        observeViewModelState()
        observeViewModelNavigator()
    }

    /**
     * Dagger component should be initialized here
     */
    protected abstract fun assignDependencies()

    /**
     * Observes view model presenter state
     */
    private fun observeViewModelState() {
        observe(viewModel.state, ::onViewModelStateChanged)
    }

    /**
     * Observe view model navigator.
     */
    private fun observeViewModelNavigator() {
        observe(viewModel.navigator, ::onNavigate)
    }

    /**
     * Called when view model state has changed
     */
    protected open fun onViewModelStateChanged(state: ViewModelState) {

    }

    /**
     * Called when view model wants to navigate to another screen
     */
    protected open fun onNavigate(navigator: ViewModelNavigator) {

    }

}

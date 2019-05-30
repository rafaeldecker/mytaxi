package com.mytaxy.test.android.screens.home

import android.os.Bundle
import com.mytaxy.test.R
import com.mytaxy.test.android.screens.base.MvvmActivity
import com.mytaxy.test.injection.ActivityComponent

class MainActivity : MvvmActivity<MainViewModel>() {

    override val viewModel: MainViewModel by lazy {
        viewModelFactory.get<MainViewModel>(this)
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.fetchData()
    }

}

package com.mytaxy.test.android.screens.home

import android.os.Bundle
import com.mytaxy.test.R
import com.mytaxy.test.android.screens.base.MvvmActivity
import com.mytaxy.test.injection.ActivityComponent

class HomeActivity : MvvmActivity<HomeViewModel>() {

    override val viewModel: HomeViewModel by lazy {
        viewModelFactory.get<HomeViewModel>(this)
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel.fetchData()
    }

}

package com.mytaxy.test.android.screens.home

import com.mytaxy.test.android.screens.base.MvvmViewModel
import com.mytaxy.test.infra.logger.Logger
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-05-28.
 */

class MainViewModel @Inject constructor(
    private val logger: Logger
): MvvmViewModel() {


    fun start() {
        print("test")
    }

}
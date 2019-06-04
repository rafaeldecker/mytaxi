package com.mytaxi.test.android.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Rafael Decker on 2019-05-30.
 */

data class HomeModel(
    val title: String,
    val items: List<HomeModelItem>
)

data class HomeModelItem(
    val id: Long,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
    @DrawableRes val backgroundResId: Int
)
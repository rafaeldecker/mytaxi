package com.mytaxy.test.util.extensions

import android.view.View

/**
 * Created by Rafael Decker on 2019-06-03.
 */

fun Boolean.visibleOrGone() =
    if (this) {
        View.VISIBLE
    } else {
        View.GONE
    }
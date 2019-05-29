package com.mytaxy.test.android.screens.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Rafael Decker on 2019-05-28.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}

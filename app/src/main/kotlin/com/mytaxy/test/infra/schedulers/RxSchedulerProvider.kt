package com.mytaxy.test.infra.schedulers

import io.reactivex.Scheduler

/**
 * Created by Rafael Decker on 2019-05-28.
 */

interface RxSchedulerProvider {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun main(): Scheduler

}

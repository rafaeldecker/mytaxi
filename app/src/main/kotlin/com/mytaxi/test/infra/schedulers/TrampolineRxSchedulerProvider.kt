package com.mytaxi.test.infra.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by Rafael Decker on 2019-05-28.
 */

class TrampolineRxSchedulerProvider : RxSchedulerProvider {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun main(): Scheduler = Schedulers.trampoline()

}

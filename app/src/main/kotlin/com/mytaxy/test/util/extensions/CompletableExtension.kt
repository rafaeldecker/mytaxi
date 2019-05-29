package com.mytaxy.test.util.extensions

import com.mytaxy.test.infra.schedulers.RxSchedulerProvider
import io.reactivex.Completable

/**
 * Created by Rafael Decker on 2019-05-28.
 */

fun Completable.subscribeOnIo(
    rxSchedulerProvider: RxSchedulerProvider
): Completable = subscribeOn(rxSchedulerProvider.io())

fun Completable.observeOnMainThread(
    rxSchedulerProvider: RxSchedulerProvider
): Completable = observeOn(rxSchedulerProvider.main())

fun Completable.saveMainThread(
    rxSchedulerProvider: RxSchedulerProvider
): Completable = subscribeOnIo(rxSchedulerProvider)
    .observeOnMainThread(rxSchedulerProvider)

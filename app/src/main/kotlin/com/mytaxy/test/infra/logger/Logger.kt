package com.mytaxy.test.infra.logger

/**
 * Created by Rafael Decker on 2019-05-28.
 */

interface Logger {

    fun start()

    fun d(tag: String, message: String?)
    fun d(message: String?)
    fun d(throwable: Throwable)

    fun e(tag: String, message: String?)
    fun e(message: String?)
    fun e(throwable: Throwable)

}

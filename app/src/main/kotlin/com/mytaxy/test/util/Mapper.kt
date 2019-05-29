package com.mytaxy.test.util

/**
 * Created by Rafael Decker on 2019-05-28.
 */

interface Mapper<In, Out> {

    fun map(param: In): Out

    fun mapList(param: List<In>): List<Out> = param.map(::map)

    fun mapReverse(param: Out): In

    fun mapListReverse(param: List<Out>): List<In> = param.map(::mapReverse)

}
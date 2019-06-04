package com.mytaxi.test.injection

import javax.inject.Scope

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope
package com.mytaxy.test.injection

import com.mytaxy.test.infra.api.RetrofitFactory
import com.mytaxy.test.infra.logger.Logger
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Rafael Decker on 2019-05-28.
 */

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logger: Logger
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        val httpLogger = HttpLoggingInterceptor.Logger { logger.d("OkHttp", it) }
        val logging = HttpLoggingInterceptor(httpLogger)
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.interceptors().add(logging)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitFactory: RetrofitFactory
    ): Retrofit = retrofitFactory.create()


//    @Provides
//    @Singleton
//    fun provideTmdbApi(
//        retrofit: Retrofit
//    ): TmdbApi = retrofit.create(TmdbApi::class.java)

}

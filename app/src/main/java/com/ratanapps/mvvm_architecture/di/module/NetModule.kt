package com.ratanapps.mvvm_architecture.di.module

import com.ratanapps.mvvm_architecture.BuildConfig
import com.ratanapps.mvvm_architecture.network.TestRetrofitApiService
import com.ratanapps.mvvm_architecture.network.constant.UrlConstant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .connectTimeout(UrlConstant.TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .readTimeout(UrlConstant.TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .writeTimeout(UrlConstant.TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(UrlConstant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun getRetrofitApiService(retrofit: Retrofit): TestRetrofitApiService = retrofit.create(TestRetrofitApiService::class.java)
}
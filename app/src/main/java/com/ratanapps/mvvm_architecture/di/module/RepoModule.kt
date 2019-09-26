package com.ratanapps.mvvm_architecture.di.module

import com.ratanapps.mvvm_architecture.myapp.MyApplication
import com.ratanapps.mvvm_architecture.network.NetworkRepository
import com.ratanapps.mvvm_architecture.network.TestRetrofitApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun getNetworkRepository(retrofitApiService: TestRetrofitApiService) = NetworkRepository(retrofitApiService)

    @Provides
    fun getApplication() = MyApplication.getApplicationInstance()
}
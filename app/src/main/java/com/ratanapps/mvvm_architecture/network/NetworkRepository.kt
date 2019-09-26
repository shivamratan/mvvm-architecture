package com.ratanapps.mvvm_architecture.network


class NetworkRepository(private val testRetrofitApiService: TestRetrofitApiService)
{
    fun getHtmlResponse() = testRetrofitApiService.getHtmlResponseFromNetwork()
}
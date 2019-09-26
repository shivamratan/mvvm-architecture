package com.ratanapps.mvvm_architecture.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface TestRetrofitApiService
{
    @GET(".")
    fun getHtmlResponseFromNetwork(): Single<ResponseBody>
}
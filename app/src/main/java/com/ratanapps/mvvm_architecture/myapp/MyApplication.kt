package com.ratanapps.mvvm_architecture.myapp

import android.app.Application
import com.ratanapps.mvvm_architecture.di.component.AppComponent
import com.ratanapps.mvvm_architecture.di.component.DaggerAppComponent

class MyApplication: Application()
{
    lateinit var appComponent: AppComponent
    companion object {
        lateinit var application: MyApplication
        fun getApplicationInstance():MyApplication = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this@MyApplication
        initDagger()
    }




    fun initDagger(){
        this.appComponent = DaggerAppComponent.builder()
                .build()
    }






}
package com.ratanapps.mvvm_architecture.di.component

import com.ratanapps.mvvm_architecture.di.module.NetModule
import com.ratanapps.mvvm_architecture.di.module.RepoModule
import com.ratanapps.mvvm_architecture.di.module.RxModule
import com.ratanapps.mvvm_architecture.di.module.ViewModelModule
import com.ratanapps.mvvm_architecture.ui.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class,NetModule::class, RepoModule::class, RxModule::class])
interface AppComponent
{
    fun inject(activity: BaseActivity)
}
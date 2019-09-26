package com.ratanapps.mvvm_architecture.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ratanapps.mvvm_architecture.viewmodel.TestViewModel
import com.ratanapps.mvvm_architecture.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TestViewModel::class)
    internal abstract fun postTestViewModel(viewModel: TestViewModel): ViewModel

}
package com.ratanapps.mvvm_architecture.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ratanapps.mvvm_architecture.myapp.MyApplication
import com.ratanapps.mvvm_architecture.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity()
{
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        bindLayout()
    }

    abstract fun bindLayout()

    fun initDagger(){
        (this.application as MyApplication).appComponent.inject(this)
    }

}
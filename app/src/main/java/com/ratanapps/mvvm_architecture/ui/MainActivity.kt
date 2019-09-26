package com.ratanapps.mvvm_architecture.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ratanapps.mvvm_architecture.R
import com.ratanapps.mvvm_architecture.databinding.ActivityMainBinding
import com.ratanapps.mvvm_architecture.ui.base.BaseActivity
import com.ratanapps.mvvm_architecture.util.extensions.toast
import com.ratanapps.mvvm_architecture.viewmodel.TestViewModel

class MainActivity : BaseActivity() {

    lateinit var mainActivityBinding: ActivityMainBinding
    val mViewModel:TestViewModel by lazy { ViewModelProviders.of(this,viewModelFactory)[TestViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLoading()
    }

    override fun bindLayout() {
        mainActivityBinding = DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
        mainActivityBinding.viewModel = mViewModel
        mainActivityBinding.executePendingBindings()
    }



    fun observeLoading(){
        mViewModel.isNetworkError.observe(this,Observer{
            if(it?:false){
                toast("Internet Connection Issue occured !")
            }
        })
    }
}

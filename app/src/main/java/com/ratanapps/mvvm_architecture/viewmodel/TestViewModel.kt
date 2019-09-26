package com.ratanapps.mvvm_architecture.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.os.AsyncTask
import com.ratanapps.mvvm_architecture.di.module.OBSERVER_ON
import com.ratanapps.mvvm_architecture.di.module.SUBCRIBER_ON
import com.ratanapps.mvvm_architecture.myapp.MyApplication
import com.ratanapps.mvvm_architecture.network.NetworkRepository
import com.ratanapps.mvvm_architecture.util.Utils
import com.ratanapps.mvvm_architecture.util.Utils.Companion.isWhiteSpaceChar
import com.ratanapps.mvvm_architecture.viewmodel.base.BaseViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class TestViewModel @Inject constructor(val networkRepository: NetworkRepository, @param:Named(SUBCRIBER_ON) private val subscriberOn: Scheduler,
                                        @param:Named(OBSERVER_ON) private val observerOn: Scheduler,val application: MyApplication):BaseViewModel(application)
{
    val isNetworkError:MutableLiveData<Boolean> = MutableLiveData()
    val showLoading:ObservableField<Boolean> = ObservableField<Boolean>()
    val showResultData:ObservableField<Boolean> = ObservableField<Boolean>()

    val resultTask1:ObservableField<String> = ObservableField()
    val resultTask2:ObservableField<String> = ObservableField()
    val resultTask3:ObservableField<String> = ObservableField()


    var isLoadedTask1 = false
    var isLoadedTask2 = false
    var isLoadedTask3 = false



    init {
        isNetworkError.value = false
    }

    fun onRequestButtonClicked(){
      //  isLoaded.value = false
      if(Utils.isNetworkConnected(application)) {
          showLoading.set(true)
          getData(RequestType.TASK1)
          getData(RequestType.TASK2)
          getData(RequestType.TASK3)
      }else{
          isNetworkError.value = true
      }
    }

    fun getData(requestType:RequestType){
        this.disposable.addAll(this.networkRepository.getHtmlResponse()
                .subscribeOn(subscriberOn)
                .observeOn(observerOn)
                .doOnSubscribe {  }
                .subscribe({

                   val str=  it?.string()?:""
                     MyWorkerAsync(requestType,str).execute()
                },{
                    it.printStackTrace()
                    showLoading.set(false)
                }))

    }

    inner class MyWorkerAsync(val requestType: RequestType,val codeStr:String): AsyncTask<Void,Void,String>(){

        override fun doInBackground(vararg params: Void): String {
            val result= when(requestType){
                RequestType.TASK1 -> parseForTask1(codeStr)
                RequestType.TASK2 -> parseForTask2(codeStr)
                RequestType.TASK3 -> parseForTask3(codeStr)
            }

            return result
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            when(requestType){
                RequestType.TASK1 -> {
                    isLoadedTask1 = true
                    resultTask1.set(result)
                }
                RequestType.TASK2 -> {
                    isLoadedTask2 = true
                    resultTask2.set(result)
                }
                RequestType.TASK3 -> {
                    isLoadedTask3 = true
                    resultTask3.set(result)
                }
            }

            showResultData.set(true)
            showLoading.set(false)
        }


        fun parseForTask1(source:String):String{
            return (source.get(10).toString())
        }

        fun parseForTask2(source:String):String{
            var builder:StringBuilder = StringBuilder()
            var init:Int = 10
            val length:Int = source.length
            builder.append("Array = ")
            while (init<length){
                if(init+10<length)
                    builder.append(source.get(init)+"  ")
                else
                    builder.append(source.get(init))

                init+=10
            }
            builder.append("")
            return (builder.toString())
        }

        fun parseForTask3(source: String):String{
            val wordMap:MutableMap<String,Int> = HashMap<String,Int>()
            val n:Int = source.length
            var state:State = State.IN
            var start=0
            var end=0
            var temp =""

            for(i in 0..n-1){
                val ch:Char = source.get(i)
                if(isWhiteSpaceChar(ch)|| i==n-1) {
                    state = State.OUT
                    end = i
                    temp = source.substring(start,end)
                    if(wordMap.contains(temp)){
                        var value:Int = wordMap.get(temp)?:0
                        value++;
                        wordMap.set(temp,value)
                    }else{
                        wordMap.put(temp,1)
                    }
                }
                else if(state == State.OUT){
                    state = State.IN
                    start = i
                }
            }

            var builder:StringBuilder = StringBuilder()
            val iterator:Iterator<Map.Entry<String,Int>> = wordMap.entries.iterator()
            while (iterator.hasNext()){
                val entry = iterator.next()
                builder.append("# \"${entry.key}\" = ${entry.value} times \n\n")
            }

            return (builder.toString())
        }

    }

    fun checkAllDataLoaded():Boolean{
        return isLoadedTask1&&isLoadedTask2&&isLoadedTask3
    }

    enum class RequestType{
        TASK1,TASK2,TASK3
    }

    enum class State{
        IN,OUT
    }




}
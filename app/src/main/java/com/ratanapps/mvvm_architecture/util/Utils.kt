package com.ratanapps.mvvm_architecture.util

import android.content.Context
import android.net.ConnectivityManager

class Utils
{
    companion object{
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
            return activeNetwork!=null
        }

        fun isWhiteSpaceChar(ch:Char):Boolean{
            return (ch==' '||ch=='\t'||ch=='\n')
        }
    }
}
package com.ravi.imageloadingappdemo.util

import android.content.Context
import android.widget.Toast

class UtilsFunctions {
    companion object {
        fun showToast(context: Context, msg:String){
            Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
        }
    }
}
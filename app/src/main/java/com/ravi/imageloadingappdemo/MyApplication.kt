package com.ravi.imageloadingappdemo

import android.app.Application
import com.ravi.imageloadingappdemo.util.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    companion object {
        var movieViewType = Constants.LIST
    }
}
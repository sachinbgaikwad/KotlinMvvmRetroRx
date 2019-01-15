package com.android.kotlinmvvmdemo.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.android.kotlinmvvmdemo.data.local.AppPreferenceManager
import com.android.kotlinmvvmdemo.data.AppDataManager
import com.android.kotlinmvvmdemo.data.remote.AppApiManager
import com.android.kotlinmvvmdemo.util.Constants.Companion.PREF_NAME

/**
 * Created by Sachin G. on 6/1/19.
 */
class KotlinMVVMDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDataManager.getInstance(
            applicationContext,
            AppPreferenceManager.getInstance(applicationContext, PREF_NAME),
            AppApiManager.getInstance(applicationContext)
        )
    }

    companion object {
        @JvmStatic
        fun getApplication() : KotlinMVVMDemoApp {
            return KotlinMVVMDemoApp()
        }
    }
}
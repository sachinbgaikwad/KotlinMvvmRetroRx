package com.android.kotlinmvvmdemo.data.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.android.kotlinmvvmdemo.BuildConfig
import com.android.kotlinmvvmdemo.util.Constants.Companion.IS_FIRST_TIME_INSTALLED

/**
 * Created by Sachin G. on 6/1/19.
 */
class AppPreferenceManager : PreferenceManager {

    private var preferences: SharedPreferences

    private constructor(mContext: Context, prefFileName: String) {
        preferences = mContext.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    override fun clearAllValues() {
        val editor = preferences.edit()
        editor.clear()
        editor.commit()
    }

    override fun isFirstTimeInstalled(): Boolean {
        return preferences.getBoolean(IS_FIRST_TIME_INSTALLED, true)
    }

    override fun persistInstall() {
        val isSuccess = preferences.edit().putBoolean(IS_FIRST_TIME_INSTALLED, false).commit()
        if (BuildConfig.DEBUG) {
            Log.d(IS_FIRST_TIME_INSTALLED, IS_FIRST_TIME_INSTALLED + " : " + isSuccess.toString())
        }
    }

    companion object {
        private var instance: AppPreferenceManager? = null

        fun getInstance(mContext: Context, prefFileName: String): AppPreferenceManager? {
            if (instance == null) {
                synchronized(AppPreferenceManager::class.java) {
                    if (instance == null) {
                        instance = AppPreferenceManager(mContext, prefFileName)
                    }
                }
            }
            return instance
        }
    }
}
package com.android.kotlinmvvmdemo.data.local

/**
 * Created by Sachin G. on 6/1/19.
 */
interface PreferenceManager {
    fun clearAllValues()

    fun isFirstTimeInstalled(): Boolean

    fun persistInstall()
}
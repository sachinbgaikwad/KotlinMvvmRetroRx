package com.android.kotlinmvvmdemo.data

import android.content.Context
import com.android.kotlinmvvmdemo.data.local.AppPreferenceManager
import com.android.kotlinmvvmdemo.data.local.PreferenceManager
import com.android.kotlinmvvmdemo.data.model.FeedResponse
import com.android.kotlinmvvmdemo.data.remote.ApiManager
import io.reactivex.Observable

/**
 * Created by Sachin G. on 6/1/19.
 */
class AppDataManager : DataManager {

    private var context: Context? = null
    private var preferenceManager: PreferenceManager? = null
    private var apiManager: ApiManager? = null

    private constructor(context: Context?, preferenceManager: AppPreferenceManager?, apiManager: ApiManager?) {
        this.context = context?.applicationContext
        this.preferenceManager = preferenceManager
        this.apiManager = apiManager
    }

    /**
     * Shared Preferences related methods implementation are here
     * */
    override fun clearAllValues() {
        preferenceManager?.clearAllValues()
    }

    override fun isFirstTimeInstalled(): Boolean? {
        return preferenceManager?.isFirstTimeInstalled()
    }

    override fun persistInstall() {
        preferenceManager?.persistInstall()
    }

    /**
     * Feeds data gets from API
     * */
    override fun getFeeds(): Observable<FeedResponse>? {
        return apiManager?.getFeeds()
    }

    /**
     * Singleton object is created using below method
     * rather than object keyword as have three params
     * */
    companion object {
        private var instance: AppDataManager? = null

        fun getInstance(
            context: Context?,
            preferenceManager: AppPreferenceManager?,
            apiManager: ApiManager?
        ): AppDataManager? {
            if (instance == null) {
                synchronized(AppDataManager::class.java) {
                    if (instance == null) {
                        instance =
                                AppDataManager(context, preferenceManager, apiManager)
                    }
                }
            }
            return instance
        }

        fun getInstance(): AppDataManager? {
            if (instance == null) {
                synchronized(AppDataManager::class.java) {
                    if (instance == null) {
                        throw NullPointerException("Null instance")
                    }
                }
            }
            return instance
        }
    }
}
package com.android.kotlinmvvmdemo.data.remote

import android.content.Context
import android.util.Log
import com.android.kotlinmvvmdemo.BuildConfig
import com.android.kotlinmvvmdemo.data.AppDataManager
import com.android.kotlinmvvmdemo.data.model.FeedResponse
import com.android.kotlinmvvmdemo.data.remote.api.FeedApi
import com.android.kotlinmvvmdemo.util.Utils
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sachin G. on 6/1/19.
 */
class AppApiManager : ApiManager {

    private var feedApi: FeedApi
    private var retrofit: Retrofit

    private fun getClient(mContext: Context?): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(mContext?.cacheDir, cacheSize)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor { chain ->
            var request = chain.request()
            request = if (Utils.isNetworkAvailable(mContext))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
        return okHttpClientBuilder.build()
    }

    override fun getFeeds(): Observable<FeedResponse>? {
        return feedApi.getFeeds()?.doOnNext {
            Log.d("FeedApi", it.toString())
        }
    }

    private constructor(mContext: Context?) {
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient(mContext))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
        feedApi = retrofit.create(FeedApi::class.java)
    }

    companion object {
        private var instance: AppApiManager? = null

        fun getInstance(mContext : Context?): AppApiManager? {
            if (instance == null) {
                synchronized(AppDataManager::class.java) {
                    if (instance == null) {
                        instance = AppApiManager(mContext)
                    }
                }
            }
            return instance
        }
    }
}
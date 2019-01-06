package com.android.kotlinmvvmdemo.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.android.kotlinmvvmdemo.view.feeds.FeedActivity
import com.android.kotlinmvvmdemo.R
import com.android.kotlinmvvmdemo.util.Constants.Companion.SPLASH_TIME

/**
 * Created by Sachin G. on 6/1/19.
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var mDelayHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mDelayHandler = Handler()
        mDelayHandler.postDelayed(mSplashRunnable, SPLASH_TIME)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mSplashRunnable)
        }
        super.onDestroy()
    }

    private val mSplashRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}
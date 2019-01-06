package com.android.kotlinmvvmdemo.view.splash

import android.support.test.rule.ActivityTestRule
import android.view.View
import com.android.kotlinmvvmdemo.R
import org.junit.*

class SplashActivityTest {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java)

    private var mActivity: SplashActivity? = null

    @Before
    fun setUp() {
        mActivity = mActivityTestRule.activity
    }

    @Test
    fun testLaunch() {
        val view : View = mActivity!!.findViewById(R.id.tvAppName)
        Assert.assertNotNull(view)
    }

    @After
    fun tearDown() {
        mActivity = null
    }
}
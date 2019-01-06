package com.android.kotlinmvvmdemo.view.feeds

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.android.kotlinmvvmdemo.R
import org.junit.*

class FeedFragmentTest {

    @get:Rule
    val mFeedActivityTestRule: ActivityTestRule<FeedActivity> = ActivityTestRule(FeedActivity::class.java)

    private var mActivity: FeedActivity? = null

    @Before
    fun setUp() {
        mActivity = mFeedActivityTestRule.activity
    }

    @Test
    fun launchTest() {
        Assert.assertNotNull(mActivity!!.findViewById(R.id.mContainer))
        val fragment = FeedFragment()
        mActivity!!.supportFragmentManager.beginTransaction().add(R.id.mContainer, fragment).commitAllowingStateLoss()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        Assert.assertNotNull(fragment.view!!.findViewById(R.id.rvFeeds))
    }

    @After
    fun tearDown() {
        mActivity = null
    }
}
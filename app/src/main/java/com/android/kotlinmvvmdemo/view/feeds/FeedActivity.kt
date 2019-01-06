package com.android.kotlinmvvmdemo.view.feeds

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.android.kotlinmvvmdemo.R
import com.android.kotlinmvvmdemo.base.BaseActivity
import com.android.kotlinmvvmdemo.util.getViewModel
import kotlinx.android.synthetic.main.activity_feeds.*

/**
 * Created by Sachin G. on 6/1/19.
 */
class FeedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)
        addFragment(R.id.mContainer, FeedFragment.newInstance(), false, FeedFragment.javaClass.simpleName)
    }
}

package com.android.kotlinmvvmdemo.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by Sachin G. on 6/1/19.
 */
open class BaseActivity : AppCompatActivity() {

    fun addFragment(mContainer: Int, fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val ft = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(mContainer, fragment, tag)
        ft.commitAllowingStateLoss()
    }
}
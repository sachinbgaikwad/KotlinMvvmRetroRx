package com.android.kotlinmvvmdemo.base

import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Sachin G. on 6/1/19.
 */
abstract class BaseFragment : Fragment() {

    abstract fun showError(error: String?)

    fun showToast(error: String?) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

}
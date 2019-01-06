package com.android.kotlinmvvmdemo.base

import android.arch.lifecycle.ViewModel
import com.android.kotlinmvvmdemo.data.AppDataManager

/**
 * Created by Sachin G. on 6/1/19.
 */
open class BaseViewModel : ViewModel() {
    var appDataManager: AppDataManager = AppDataManager.getInstance()!!
}
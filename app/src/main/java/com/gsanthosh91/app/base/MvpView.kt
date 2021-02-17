package com.gsanthosh91.app.base

import android.app.Activity

interface MvpView {
    fun activity(): Activity
    fun onError(error: Throwable?)
}
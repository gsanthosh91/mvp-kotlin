package com.gsanthosh91.app.base

import android.app.Activity
import com.gsanthosh91.app.MvpApplication

interface MvpPresenter<V : MvpView> {
    fun activity(): Activity?
    fun appContext(): MvpApplication?
    fun attachView(mvpView: V)
    fun detachView()
}
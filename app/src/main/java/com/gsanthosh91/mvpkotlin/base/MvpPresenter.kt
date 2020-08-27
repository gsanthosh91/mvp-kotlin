package com.gsanthosh91.mvpkotlin.base

import android.app.Activity

interface MvpPresenter<V : MvpView> {
    fun activity(): Activity?
    fun attachView(mvpView: V)
    fun detachView()
}
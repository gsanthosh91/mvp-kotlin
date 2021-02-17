package com.gsanthosh91.app.ui.splash

import com.gsanthosh91.app.base.MvpPresenter
import com.gsanthosh91.app.base.MvpView

interface SplashIPresenter<V : SplashIPresenter.SplashIView> : MvpPresenter<V> {

    fun checkForUpdate()

    interface SplashIView : MvpView {
        //fun todos(todoList: List<TodoItem>)
    }
}
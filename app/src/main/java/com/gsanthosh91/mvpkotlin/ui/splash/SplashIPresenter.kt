package com.gsanthosh91.mvpkotlin.ui.splash

import com.gsanthosh91.mvpkotlin.base.MvpPresenter
import com.gsanthosh91.mvpkotlin.base.MvpView
import com.gsanthosh91.mvpkotlin.data.TodoItem

interface SplashIPresenter<V : SplashIPresenter.SplashIView> : MvpPresenter<V> {

    fun checkForUpdate()

    interface SplashIView : MvpView {
        //fun todos(todoList: List<TodoItem>)
    }
}
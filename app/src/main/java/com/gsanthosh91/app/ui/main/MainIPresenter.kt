package com.gsanthosh91.app.ui.main

import com.gsanthosh91.app.base.MvpPresenter
import com.gsanthosh91.app.base.MvpView
import com.gsanthosh91.app.data.TodoItem

interface MainIPresenter<V : MainIPresenter.MainIView> : MvpPresenter<V> {

    fun todos()

    interface MainIView : MvpView {
        //fun todos(todoList: List<TodoItem>)
    }
}
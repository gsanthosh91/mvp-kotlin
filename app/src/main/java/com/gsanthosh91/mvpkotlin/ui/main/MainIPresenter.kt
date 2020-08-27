package com.gsanthosh91.mvpkotlin.ui.main

import com.gsanthosh91.mvpkotlin.base.MvpPresenter
import com.gsanthosh91.mvpkotlin.base.MvpView
import com.gsanthosh91.mvpkotlin.data.TodoItem
import java.util.*

interface MainIPresenter<V : MainIPresenter.TicketIView> : MvpPresenter<V> {

    fun todos()

    interface TicketIView : MvpView {
        fun todos(todoList: List<TodoItem>)
    }
}
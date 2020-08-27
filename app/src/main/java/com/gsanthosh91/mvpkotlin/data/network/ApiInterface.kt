package com.gsanthosh91.mvpkotlin.data.network

import com.gsanthosh91.mvpkotlin.data.TodoItem
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("todos")
    fun todos(): Observable<List<TodoItem>>
}
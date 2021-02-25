package com.gsanthosh91.app.ui.main

import com.gsanthosh91.app.base.BasePresenter
import com.gsanthosh91.app.data.network.APIClient
import com.gsanthosh91.app.data.network.ApiInterface

class MainPresenter<V : MainIPresenter.MainIView> : BasePresenter<V>(), MainIPresenter<V> {

    private var apiClient: ApiInterface = APIClient.client

    override fun todos() {
        /*val modelObservable: Observable<List<TodoItem>> = apiClient.todos()
        modelObservable.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getMvpView().todos(it)
            }) {
                val e = it as Throwable
                getMvpView().onError(e)
            }*/
    }
}
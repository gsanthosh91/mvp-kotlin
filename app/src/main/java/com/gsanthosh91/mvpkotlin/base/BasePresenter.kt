package com.gsanthosh91.mvpkotlin.base

import android.app.Activity
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : MvpView> {
    var compositeDisposable: CompositeDisposable? = null

    fun BasePresenter() {
        compositeDisposable = CompositeDisposable()
    }

    private var mMvpView: V? = null

    fun activity(): Activity? {
        return getMvpView().activity()
    }

    fun attachView(mvpView: V) {
        mMvpView = mvpView
    }

    fun detachView() {
        mMvpView = null
        compositeDisposable?.dispose()
    }

    fun isViewAttached(): Boolean {
        return mMvpView != null
    }

    fun getMvpView(): V {
        return mMvpView!!
    }

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException(
        "Please call Presenter.attachView(MvpView) before" +
                " requesting data to the Presenter"
    )
}
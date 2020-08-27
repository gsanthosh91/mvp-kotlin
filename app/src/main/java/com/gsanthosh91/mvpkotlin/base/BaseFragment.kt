package com.gsanthosh91.mvpkotlin.base

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseFragment : Fragment(), MvpView {

    var view1: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (view1 == null) {
            view1 = inflater.inflate(getLayoutId(), container, false)
            initView(view1)
        }
        return view1
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(view: View?): View?

    override fun activity(): Activity {
        return activity!!
    }

    override fun onError(error: Throwable?) {
        Log.e("onError", "onError: " + error.toString())
        when (error) {
            is HttpException -> {
                val response: Response<*>? = error.response()
                val jObjError = JSONObject(response!!.errorBody()!!.string())
                if (jObjError.has("error")) {
                    Toast.makeText(activity(), jObjError.optString("error"), Toast.LENGTH_SHORT)
                        .show()
                }
            }
            is UnknownHostException -> {
                Toast.makeText(
                    activity(),
                    "Please check your internet connection!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}
package com.gsanthosh91.mvpkotlin.base

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseActivity : AppCompatActivity(), MvpView {

    override fun activity(): Activity = this

    abstract fun getLayoutId(): Int

    abstract fun initView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initView()
    }

    @TargetApi(Build.VERSION_CODES.M)
    open fun hasPermission(permission: String): Boolean {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    open fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        requestPermissions(permissions, requestCode)
    }

    open fun dialPhone(phone: String) {
        startActivity(Intent(Intent.ACTION_DIAL).apply { data = Uri.parse("tel:$phone") })
    }

    open fun openWeb(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
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
package com.gsanthosh91.app.base

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gsanthosh91.app.R
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.File
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

    fun download(url: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            downloadFile(url)
        } else {
            if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) && hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                downloadFile(url)
            } else {
                Toast.makeText(activity(), "Permission Required to Download", Toast.LENGTH_SHORT)
                    .show()
                requestPermissionsSafely(
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), 101
                )
            }
        }

    private fun downloadFile(url: String) {

        val fileName: String = url.substring(url.lastIndexOf('/') + 1, url.length)
        val direct = File(getExternalFilesDir(null), "/${R.string.app_name}")
        if (!direct.exists()) {
            direct.mkdirs()
        }
        val mgr = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri = Uri.parse(url)
        val request = DownloadManager.Request(
            downloadUri
        )
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setAllowedOverRoaming(false)
            .setTitle(getString(R.string.app_name)) //Download Manager Title
            .setDescription("Downloading...") //Download Manager description
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                "/${R.string.app_name}/" + fileName  //Your User define(Non Standard Directory name)/File Name
            )

        val downloadID = mgr.enqueue(request)
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
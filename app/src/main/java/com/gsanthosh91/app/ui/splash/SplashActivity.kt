package com.gsanthosh91.app.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.gsanthosh91.app.BuildConfig
import com.gsanthosh91.app.R
import com.gsanthosh91.app.base.BaseActivity
import com.gsanthosh91.app.databinding.ActivitySplashBinding
import com.gsanthosh91.app.ui.main.MainActivity
import com.pixplicity.easyprefs.library.Prefs

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity(), SplashIPresenter.SplashIView {

    private var presenter: SplashPresenter<SplashActivity> = SplashPresenter()
    private lateinit var binding: ActivitySplashBinding


    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        presenter.attachView(this)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.message.text = "version ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"



        Handler(Looper.getMainLooper()).postDelayed({
            if (Prefs.getBoolean("logged_in", false)) {
                val mainIntent = Intent(activity(), MainActivity::class.java)
                startActivity(mainIntent)
                finishAffinity()
            } else {
                val mainIntent = Intent(activity(), MainActivity::class.java)
                startActivity(mainIntent)
                finishAffinity()
            }
        }, 2000)
    }


}
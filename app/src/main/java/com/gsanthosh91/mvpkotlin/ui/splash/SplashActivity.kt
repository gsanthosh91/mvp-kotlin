package com.gsanthosh91.mvpkotlin.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.gsanthosh91.mvpkotlin.BuildConfig
import com.gsanthosh91.mvpkotlin.R
import com.gsanthosh91.mvpkotlin.base.BaseActivity
import com.gsanthosh91.mvpkotlin.databinding.ActivitySplashBinding
import com.gsanthosh91.mvpkotlin.ui.main.MainActivity
import com.pixplicity.easyprefs.library.Prefs

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
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finishAffinity()
            } else {
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finishAffinity()
            }
        }, 2000)
    }


}
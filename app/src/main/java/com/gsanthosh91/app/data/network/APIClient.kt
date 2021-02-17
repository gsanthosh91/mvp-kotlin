package com.gsanthosh91.app.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gsanthosh91.app.BuildConfig
import com.gsanthosh91.app.MvpApplication
import com.pixplicity.easyprefs.library.Prefs
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class APIClient {


    companion object {
        private var retrofit: Retrofit? = null
        val client: ApiInterface
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .client(getHttpClient())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit!!.create(ApiInterface::class.java)
            }

        private fun getHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            return OkHttpClient().newBuilder()
                .cache(Cache(MvpApplication.mInstance.getCacheDir(), 10 * 1024 * 1024))
                .addNetworkInterceptor(StethoInterceptor())
                .connectTimeout(5, TimeUnit.MINUTES)
                .addNetworkInterceptor(AddHeaderInterceptor())
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .build()
        }

    }


    private class AddHeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request().newBuilder()
            builder.addHeader("X-Requested-With", "XMLHttpRequest")
            builder.addHeader("Accept", "application/json")
            builder.addHeader("Authorization", Prefs.getString("access_token", ""))
            return chain.proceed(builder.build())
        }
    }
}
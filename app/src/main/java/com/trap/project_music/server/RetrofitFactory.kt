package com.trap.project_music.server

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import com.trap.project_music.BuildConfig
import com.trap.project_music.dal.dao.AccountDAO
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitFactory(val context: Context) {
    private val baseUrl = "https://music.gryt.tech/"

    fun <S> createService(serviceClass: Class<S>): S {
        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request = chain.request()
            val token : String? = context.getSharedPreferences("ACCOUNT",Context.MODE_PRIVATE).getString("authToken",null)
            if(token != null){
                val headers = request.headers.newBuilder().add("Authorization", "Token $token").build()
                request = request.newBuilder().headers(headers).build()
            }
            Log.v("test","tokensaved : $token")
            chain.proceed(request)

        }

        val clientBuilder = OkHttpClient.Builder().addInterceptor(headerAuthorizationInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(clientBuilder.build())
            .build()

        return retrofit.create(serviceClass)
    }

}
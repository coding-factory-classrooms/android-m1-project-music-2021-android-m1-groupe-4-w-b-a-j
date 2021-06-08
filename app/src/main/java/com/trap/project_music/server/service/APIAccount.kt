package com.trap.project_music.server.service

import com.trap.project_music.model.Account
import com.trap.project_music.model.NewAccount
import com.trap.project_music.server.model.Auth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIAccount {
    @POST("api/auth")
    fun getToken(@Body authRequest: Auth.Request) : Call<Auth.Response>
    @POST("api/signup")
    fun signup(@Body account: NewAccount.Request) : Call<NewAccount.Response>
    @GET("api/auth/check")
    fun checkToken(): Call<String>
    @GET("api/signup/email/{email}")
    fun isEmailAlreadyUse(@Path(value = "email") email: String): Call<Boolean>
    @GET("api/signup/name/{name}")
    fun isNameAlreadyUse(@Path(value = "name") email: String): Call<Boolean>
}
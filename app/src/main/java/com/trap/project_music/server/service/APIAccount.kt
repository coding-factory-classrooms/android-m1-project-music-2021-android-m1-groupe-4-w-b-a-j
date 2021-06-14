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
    @POST("/api-token-auth/")
    fun getToken(@Body authRequest: Auth.Request) : Call<Auth.Response>
}
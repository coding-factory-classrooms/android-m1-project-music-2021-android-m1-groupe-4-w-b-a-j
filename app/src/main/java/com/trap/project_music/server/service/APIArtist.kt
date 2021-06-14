package com.trap.project_music.server.service

import com.trap.project_music.vo.ArtistJSON
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIArtist {
    @GET("api/data")
    fun getArtists(): Call<List<ArtistJSON>>

    @GET("api/artist/{artistID}")
    fun getArtistsById(@Path(value = "artistID") artistID: Long): Call<ArtistJSON>

}
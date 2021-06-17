package com.ldo.project_music.api.service

import com.ldo.project_music.model.Artist
import retrofit2.Call
import retrofit2.http.*

interface APIArtist {
    @GET("api/artists/")
    fun getArtists(): Call<List<Artist>>

    @GET("api/artists/{artistID}")
    fun getArtistsById(@Path(value = "artistID") artistID: Long): Call<Artist>

}
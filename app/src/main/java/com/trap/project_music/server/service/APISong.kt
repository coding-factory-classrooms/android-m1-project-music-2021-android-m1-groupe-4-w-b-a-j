package com.trap.project_music.server.service

import com.trap.project_music.vo.ArtistJSON
import com.trap.project_music.vo.SongJSON
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APISong {

    @GET("api/songs/")
    fun getSongsByArtistID(@Query(value = "artist__id") artistID: Long): Call<List<SongJSON>>
}
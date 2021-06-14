package com.trap.project_music.vo

import com.squareup.moshi.JsonClass
import com.trap.project_music.model.Account
import com.trap.project_music.model.Category

@JsonClass(generateAdapter = true)
data class ArtistJSON(
    val id: Long,
    val name: String,
    val genre_name: String,
    val album_cover_url: String?
)

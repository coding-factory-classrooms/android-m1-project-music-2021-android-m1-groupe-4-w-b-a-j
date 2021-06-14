package com.trap.project_music.vo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongJSON(
    val id : Long,
    val name : String,
    val file: String,
    val duration: Int,
    val created_at: String,
    val artist : Long
)

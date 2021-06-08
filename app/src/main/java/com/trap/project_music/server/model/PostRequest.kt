package com.trap.project_music.server.model

import com.squareup.moshi.JsonClass
import java.io.File


@JsonClass(generateAdapter = true)
data class PostRequest(
    val title : String? = null,
    val description : String? = null,
    val idCategory : Long? = null

)
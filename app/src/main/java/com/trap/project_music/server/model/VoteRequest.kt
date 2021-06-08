package com.trap.project_music.server.model

import com.squareup.moshi.JsonClass
import com.trap.project_music.enums.VoteType

@JsonClass(generateAdapter = true)
data class VoteRequest (
    val idPost: Long,
    val choice : VoteType
)
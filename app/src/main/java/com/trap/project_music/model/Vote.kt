package com.trap.project_music.model

import com.squareup.moshi.JsonClass
import com.trap.project_music.enums.VoteType

@JsonClass(generateAdapter = true)
data class Vote(
    val idAccount: Long,
    val idPostJSON: Long,
    val choice: VoteType
)
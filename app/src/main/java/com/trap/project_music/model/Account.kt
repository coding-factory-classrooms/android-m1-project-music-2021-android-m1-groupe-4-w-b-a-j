package com.trap.project_music.model

import com.squareup.moshi.JsonClass
import com.trap.project_music.vo.SongJSON

enum class GenderType{
    MALE,
    FEMALE,
    UNKNOWN
}

@JsonClass(generateAdapter = true)
data class Account(
    var name: String,
    var email: String,
    var gender: GenderType,
    var birthdate : String,
    var profilePictureData : SongJSON
)
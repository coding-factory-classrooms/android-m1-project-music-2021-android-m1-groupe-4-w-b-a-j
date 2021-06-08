package com.trap.project_music.server.model

import com.squareup.moshi.JsonClass


sealed class Auth{
    @JsonClass(generateAdapter = true)
    data class Request(val username: String,val password:String)

    @JsonClass(generateAdapter = true)
    data class Response(val token: String)
}

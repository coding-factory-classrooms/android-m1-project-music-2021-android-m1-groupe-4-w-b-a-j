package com.trap.project_music.server.model

import com.squareup.moshi.JsonClass


sealed class Auth{
    @JsonClass(generateAdapter = true)
    data class Request(val username: String = "groupe4",val password:String = "a2Tq2D7lg1")

    @JsonClass(generateAdapter = true)
    data class Response(val token: String)
}

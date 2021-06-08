package com.trap.project_music.model

import com.squareup.moshi.JsonClass
import com.trap.project_music.server.model.Auth
import java.time.LocalDate


sealed class NewAccount{
    @JsonClass(generateAdapter = true)
    data class Request(    var name: String? = null,
                           var password: String? = null,
                           var email: String? = null,
                           var gender: GenderType? = null,
                           var birthdate: String? = null
    )
    @JsonClass(generateAdapter = true)
    data class Response(val accountDTO: Account,val jwtResponse: Auth.Response)
}


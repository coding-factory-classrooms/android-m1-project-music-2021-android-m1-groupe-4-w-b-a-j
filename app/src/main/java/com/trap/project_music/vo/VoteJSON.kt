package com.trap.project_music.vo

import com.trap.project_music.enums.VoteType
import com.trap.project_music.model.Account

data class VoteJSON (
    val idAccount: Account,
    val idArtistJSON: ArtistJSON,
    val choice : VoteType
)
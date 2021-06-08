package com.trap.project_music.ui.main.home.listener

import com.trap.project_music.enums.VoteType

interface OnPostClickListener {
    fun  invoke(voteType: VoteType, idPost: Long)
}
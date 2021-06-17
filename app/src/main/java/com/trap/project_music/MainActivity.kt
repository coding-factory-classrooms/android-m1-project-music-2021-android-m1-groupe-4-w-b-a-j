package com.trap.project_music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.trap.project_music.dal.entity.Playlist

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playlistDao = PlaylistAndMusicApplication.database.playlistDao()
//        playlistDao.deleteAll()
//        for (i in 1..10) {
//            playlistDao.insert(Playlist(0,"ma playlist $i",R.drawable._667_photo_min))
//        }

        navController = findNavController(R.id.mainActivity)
    }
}
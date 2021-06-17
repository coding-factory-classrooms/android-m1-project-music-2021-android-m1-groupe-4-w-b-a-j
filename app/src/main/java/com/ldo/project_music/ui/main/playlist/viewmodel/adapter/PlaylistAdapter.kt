package com.ldo.project_music.ui.main.playlist.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ldo.project_music.R
import com.ldo.project_music.databinding.PlaylistBinding
import com.ldo.project_music.ui.main.playlist.viewmodel.PlaylistInfo

class PlaylistAdapter (private val playlistInfo: List<PlaylistInfo>, private val onPlaylistClickListener: OnPlaylistClickListener)  : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding : PlaylistBinding = PlaylistBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.playlist, parent , false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualPlaylist = playlistInfo[position].playlist
        val actualSongs = playlistInfo[position].songs
        holder.binding.playlist = actualPlaylist
        holder.binding.songsCount.text = actualSongs.size.toString()

        holder.binding.playlistContainer.setOnClickListener {
            onPlaylistClickListener.invoke(actualPlaylist)
        }
    }

    override fun getItemCount(): Int { return playlistInfo.size }
}
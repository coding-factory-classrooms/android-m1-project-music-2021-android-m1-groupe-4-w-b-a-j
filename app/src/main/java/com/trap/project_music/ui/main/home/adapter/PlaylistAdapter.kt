package com.trap.project_music.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trap.project_music.databinding.PlaylistItemBinding
import com.trap.project_music.model.Playlist

class PlaylistAdapter(private var playlists: List<Playlist>)
    : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {
    class ViewHolder(val binding: PlaylistItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlist = playlists[position]
        with (holder.binding) {
            //playlistImageView.setImageResource(playlist.image)
            playlistNameTextView.text = playlist.name
        }

    }

    override fun getItemCount(): Int = playlists.size

    fun updateDataSet(playlists: List<Playlist>) {
        this.playlists = playlists
        notifyDataSetChanged()
    }
}
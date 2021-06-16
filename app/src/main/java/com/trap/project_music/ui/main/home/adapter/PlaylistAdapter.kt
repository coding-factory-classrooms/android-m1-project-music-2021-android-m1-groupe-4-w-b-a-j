package com.trap.project_music.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trap.project_music.databinding.PlaylistItemBinding
import com.trap.project_music.model.PlaylistModel

class PlaylistAdapter(private var playlistModels: List<PlaylistModel>)
    : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {
    class ViewHolder(val binding: PlaylistItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlist = playlistModels[position]
        with (holder.binding) {
            playlistImageView.setImageResource(playlist.imageId)
            playlistNameTextView.text = playlist.name
        }

    }

    override fun getItemCount(): Int = playlistModels.size

    fun updateDataSet(playlistModels: List<PlaylistModel>) {
        this.playlistModels = playlistModels
        notifyDataSetChanged()
    }
}
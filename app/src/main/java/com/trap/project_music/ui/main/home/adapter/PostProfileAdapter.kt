package com.trap.project_music.ui.main.home.adapter


import android.annotation.SuppressLint
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.trap.project_music.R
import com.trap.project_music.databinding.PostProfileBinding
import com.trap.project_music.ui.main.home.listener.OnArtistClickListener
import com.trap.project_music.vo.ArtistJSON


class PostProfileAdapter(
    private val artists: List<ArtistJSON>,
    private val onArtistClickListener: OnArtistClickListener
) : RecyclerView.Adapter<PostProfileAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: PostProfileBinding = PostProfileBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.post_profile, parent, false)
        return ViewHolder(layoutInflater)
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualArtist: ArtistJSON = artists[position]
        holder.binding.postProfile = actualArtist
    }


    override fun getItemCount(): Int {
        return artists.size
    }
}
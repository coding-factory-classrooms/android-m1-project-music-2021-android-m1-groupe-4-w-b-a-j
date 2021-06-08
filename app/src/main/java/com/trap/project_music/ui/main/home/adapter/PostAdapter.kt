package com.trap.project_music.ui.main.home.adapter


import android.annotation.SuppressLint
import android.app.Activity
import android.util.DisplayMetrics
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trap.project_music.R
import com.trap.project_music.databinding.PostBinding
import com.trap.project_music.enums.VoteType
import com.trap.project_music.ui.main.home.listener.OnDoubleClickListener
import com.trap.project_music.ui.main.home.listener.OnPostClickListener
import com.trap.project_music.vo.PostJSON


class PostAdapter (private val posts: List<PostJSON>, private val onPostClickListener: OnPostClickListener) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding : PostBinding = PostBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.post, parent , false)
        return ViewHolder(layoutInflater)
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualPost : PostJSON = posts[position]
        holder.binding.post = actualPost

        Glide.with(holder.itemView)
            .load(actualPost.profilePicture)
            .into(holder.binding.profilePicture)

        holder.binding.artistName.text = actualPost.name


        holder.binding.genre.text = "Genre: ${actualPost.genre}"
        holder.binding.songNumber.text = "Nombre de chanson(s) : ${actualPost.songs.size}"

    }


    override fun getItemCount(): Int { return posts.size }
}
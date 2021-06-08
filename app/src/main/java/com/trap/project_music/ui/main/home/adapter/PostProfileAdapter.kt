package com.trap.project_music.ui.main.home.adapter


import android.annotation.SuppressLint
import android.app.Activity
import android.util.DisplayMetrics
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.trap.project_music.R
import com.trap.project_music.databinding.PostProfileBinding
import com.trap.project_music.ui.main.home.listener.OnPostClickListener
import com.trap.project_music.vo.PostJSON


class PostProfileAdapter(
    private val posts: List<PostJSON>,
    private val onPostClickListener: OnPostClickListener
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
        val actualPost: PostJSON = posts[position]
        holder.binding.postProfile = actualPost
        // GET DISPLAY SIZE FOR IMAGES
        val displaymetrics = DisplayMetrics()
        (holder.itemView.context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)
        val devicewidth = (displaymetrics.widthPixels * 0.9).toInt()
        val postWidth = (devicewidth / 2)
        val imageWidth = (postWidth / 2) - (holder.binding.profilePostContainer.paddingLeft)


        val height: Int = (postWidth * 1.33).toInt()
        val imageHeight: Int = (imageWidth * 1.33).toInt()

        holder.binding.profilePostContainer.layoutParams.width = postWidth

        // IMAGE SIZING AND LOADING
        holder.binding.imageContainer.layoutParams = LinearLayout.LayoutParams((postWidth * 0.95).toInt(), (height/(2*1.05)).toInt())
        holder.binding.leftImage.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)
        holder.binding.rightImage.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)

    }


    override fun getItemCount(): Int {
        return posts.size
    }
}
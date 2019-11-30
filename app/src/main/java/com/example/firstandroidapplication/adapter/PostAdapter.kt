package com.example.firstandroidapplication.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.firstandroidapplication.R
import android.net.Uri
import com.example.firstandroidapplication.dto.*

class PostAdapter(val posts: MutableList<Post>, private val advertisingPosts: MutableList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val mixedPosts = mutableListOf<Post>()
        posts.forEach {
            if (posts.indexOf(it) > 0 && posts.indexOf(it) % 3 == 0 && advertisingPosts.size > 0){
                mixedPosts.add(advertisingPosts[0])
            }
            else {
                mixedPosts.add(it)
            }
        }
        holder.bind(mixedPosts[position])
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val userImageView: ImageView = view.findViewById(R.id.avatarImgView)
        private val created: TextView = view.findViewById(R.id.currentDateText)
        private val author: TextView = view.findViewById(R.id.author)
        private var content: TextView = view.findViewById(R.id.textView)

        private val likesCount: TextView = view.findViewById(R.id.likesCount)
        private val repostsCount: TextView = view.findViewById(R.id.sharedCount)
        private val commentsCount: TextView = view.findViewById(R.id.commentsCount)

        private val likeButton: ImageButton = view.findViewById(R.id.likeButton)
        private val commentButton: ImageButton = view.findViewById(R.id.commentButton)
        private val repostButton: ImageButton = view.findViewById(R.id.shareButton)
        private val playVideoButton: ImageButton = view.findViewById(R.id.playVideoButton)
        private val locationButton: ImageButton = view.findViewById(R.id.locationBtn)
        private val advertisingButton: ImageButton = view.findViewById(R.id.advertisingLinkButton)
        private val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)

        fun bind(post: Post) {

            created.text = howLongAgo(post.created)
            author.text = post.author
            content.text = post.content

            with(post) {

                if (geo != null){
                    locationButton.visibility = View.VISIBLE

                    locationButton.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data =
                                Uri.parse("geo:${geo.lat},${geo.long}")
                        }
                        startActivity(itemView.context, intent, null)
                    }
                }
                else {
                    locationButton.visibility = View.INVISIBLE
                }

                if (mediaURL != null){
                    playVideoButton.visibility = View.VISIBLE

                    playVideoButton.setOnClickListener {
                        val address = Uri.parse(mediaURL)
                        val openLinkIntent = Intent(Intent.ACTION_VIEW, address)

                        startActivity(itemView.context, openLinkIntent, null)
                    }
                }
                else {
                    playVideoButton.visibility = View.INVISIBLE
                }

                if (advertisingURL != null){
                    advertisingButton.visibility = View.VISIBLE
                    deleteButton.visibility = View.VISIBLE

                    advertisingButton.setOnClickListener {
                        val webpage: Uri = Uri.parse(advertisingURL)
                        val intent = Intent(Intent.ACTION_VIEW, webpage)
                        startActivity(itemView.context, intent, null)
                    }

                    deleteButton.setOnClickListener {
                        posts.removeAt(adapterPosition)
                        notifyItemRemoved(adapterPosition)
                    }
                }
                else {
                    advertisingButton.visibility = View.INVISIBLE
                    deleteButton.visibility = View.INVISIBLE
                }

                likesCount.text = likes.count.toString()
                commentsCount.text = comments.count.toString()
                repostsCount.text = reposts.count.toString()

                if (likes.count == 0){
                    likesCount.text = ""
                }

                if (comments.count == 0){
                    commentsCount.text = ""
                }
                if (reposts.count == 0) {
                    repostsCount.text = ""
                }

                if (likes.userLikes) {
                    likeButton.setImageResource(R.drawable.ic_favorite_active_24dp)
                    likesCount.setTextColor(Color.RED)
                }

                likeButton.setOnClickListener {
                    likes.userLikes = !likes.userLikes
                    likeButton.setImageResource(
                        if (likes.userLikes) {
                            R.drawable.ic_favorite_active_24dp
                        } else {
                            R.drawable.ic_favorite_inactive_24dp
                        }
                    )

                    if (likes.userLikes) {
                        likes.count++
                        likesCount.setTextColor(Color.RED)
                    } else {
                        likes.count--
                        likesCount.setTextColor(Color.rgb(80,80, 80))
                    }

                    if (likes.count > 0) {
                        likesCount.text = likes.count.toString()
                    } else {
                        likesCount.text = ""
                    }
                }

                repostButton.setOnClickListener {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT, """
                            ${post.author} (${post.created})
        
                            ${post.content}
                        """.trimIndent()
                        )
                        type = "text/plain"
                    }
                    startActivity(itemView.context, intent, null)
                }
            }
        }
    }
}

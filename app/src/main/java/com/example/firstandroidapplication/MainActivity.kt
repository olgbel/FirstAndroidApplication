package com.example.firstandroidapplication

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dto.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val likes = Likes(1, userLikes = false, canPublish = true)
        val comments = Comments(3,
            canPost = true,
            groupsCanPost = true,
            canClose = true,
            canOpen = true
        )
        val reposts = Reposts(1, true)
        val geo = Geo(1, -90.9, 90.0, "SPb VO 7 64")
        val post = EventPost(
            1,
            "Netology",
            "First post in our network!",
            "20 august 2019",
            comments,
            likes,
            reposts,
            "SPb",
            geo
            )

        currentDateText.text = post.created
        textView.text = post.content
        author.text = post.author

        val defaultTextColor = likesCount.currentTextColor

        if (post.likes.userLikes) {
            likeButton.setImageResource(R.drawable.ic_favorite_active_24dp)
            likesCount.setTextColor(Color.RED)
        }

        if (post.likes.count > 0){
            likesCount.text = post.likes.count.toString()
        }

        if (post.reposts.count > 0){
            sharedCount.text = post.reposts.count.toString()
        }

        if (post.comments.count > 0){
            commentsCount.text = post.comments.count.toString()
        }
//        val simpleDateFormat = SimpleDateFormat("dd MMM yyyy")
//        val currentDate = simpleDateFormat.format(Date())
//
//        currentDateText.text = currentDate

        // третья задача
        val seconds: Long = System.currentTimeMillis() / 1000
        val longAgo: String = howLongAgo(seconds.toInt())

        currentDateText.text = longAgo

        likeButton.setOnClickListener{
            post.likes.userLikes = !post.likes.userLikes
            likeButton.setImageResource(
                if (post.likes.userLikes) {
                    R.drawable.ic_favorite_active_24dp
            }
            else {
                    R.drawable.ic_favorite_inactive_24dp
                })

            if (post.likes.userLikes)
            {
                post.likes.count++
            }
            else {
                post.likes.count--
                likesCount.setTextColor(defaultTextColor)
            }

            println("likes count: ${post.likes.count}")
            if (post.likes.count > 0){
                likesCount.text = post.likes.count.toString()
            }
            else {
                likesCount.text = ""
            }
        }

        shareButton.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, """
                    ${post.author} (${post.created})
                    
                    ${post.content}
                """.trimIndent())
                type = "text/plain"
            }
            startActivity(intent)
        }

        locationBtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:${post.geo.lat},${post.geo.long}")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }


}

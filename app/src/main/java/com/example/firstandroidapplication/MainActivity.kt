package com.example.firstandroidapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dto.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val likes = Likes(5, userLikes = true, canPublish = true)
        val comments = Comments(3,
            canPost = true,
            groupsCanPost = true,
            canClose = true,
            canOpen = true
        )
        val reposts = Reposts(1, true)

        val post = Post(
            1,
            "Netology",
            "First post in our network!",
            "20 august 2019",
            comments,
            likes,
            reposts
            )
        currentDateText.text = post.created
        textView.text = post.content
        author.text = post.author

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

        println(longAgo)
    }


}

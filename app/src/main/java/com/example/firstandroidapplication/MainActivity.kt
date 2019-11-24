package com.example.firstandroidapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dto.*
import dto.adapter.PostAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val posts = listOf(
            Post(4, "Netology", "Kotlin MeetUp!", 1566818240, Comments(1), Likes(1, true), Reposts(5)),
            EventPost(2, "Netology", "See where you are!", 1566418240, Comments(5), Likes(5, true), Reposts(0), Geo(1, -90.9, 90.0, "SPb VO 7 64")),
            MediaPost(3, "Netology", "Watch our video!", 1566518240, Comments(0), Likes(3, false), Reposts(1), "https://www.youtube.com/watch?v=WhWc3b3KhnY"),
            Post(2, "Netology", "Our network is growing!", 786756, Comments(5), Likes(5, true), Reposts(0)),
            Post(1,  "Netology", "First it in our network!", 9955334, Comments(9), Likes(0, false), Reposts(2)),
            AdvertisingPost(1, "Netology", "Go through the link: ", 9090, Comments(9), Likes(0, false), Reposts(2), "https://netology.ru/")
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = PostAdapter(posts)
    }


}

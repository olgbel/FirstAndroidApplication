package com.example.firstandroidapplication.gson

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.firstandroidapplication.dto.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val list = listOf(
        Post(
            4,
            PostType.POST,
            "Netology",
            "Kotlin MeetUp!",
            1566818240,
            Comments(1),
            Likes(1, true),
            Reposts(5),
            null
        ),
        Post(
            3,
            PostType.EVENT,
            "Netology",
            "See where you are!",
            1566418240,
            Comments(5),
            Likes(5, true),
            Reposts(0),
            Geo(1, -90.9, 90.0, "SPb VO 7 64")
        ),
        Post(
            2,
            PostType.MEDIA,
            "Netology",
            "Watch our video!",
            1566518240,
            Comments(0),
            Likes(3, false),
            Reposts(1),
            null,
            "https://www.youtube.com/watch?v=WhWc3b3KhnY"
        ),
        Post(
            6,
            PostType.POST,
            "Netology",
            "Our network is growing!",
            786756,
            Comments(5),
            Likes(5, true),
            Reposts(0),
            null
        ),
        Post(
            1,
            PostType.POST,
            "Netology",
            "First it in our network!",
            9955334,
            Comments(9),
            Likes(0, false),
            Reposts(2),
            null
        )
    )

    println(Gson().toJson(list))

    val gson = GsonBuilder().apply {
        setPrettyPrinting()
        serializeNulls()
    }.create()

    Files.write(Paths.get("./posts.json"), gson.toJson(list).toByteArray(), StandardOpenOption.CREATE)
}
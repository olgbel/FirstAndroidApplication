package com.example.firstandroidapplication.dto

open class Post(val id: Long,
//                val type: PostType,
                val author: String,
                var content: String,
                val created: Int,
                val comments: Comments = Comments(),
                val likes: Likes = Likes(),
                val reposts: Reposts = Reposts()
)
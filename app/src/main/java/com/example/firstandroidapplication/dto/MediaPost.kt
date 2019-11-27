package com.example.firstandroidapplication.dto

class MediaPost(id: Long,
//                type: PostType = PostType.MEDIA,
                author: String,
                content: String,
                created: Int,
                comments: Comments,
                likes: Likes,
                reposts: Reposts,
                val url: String): Post(id, author, content, created, comments, likes, reposts)
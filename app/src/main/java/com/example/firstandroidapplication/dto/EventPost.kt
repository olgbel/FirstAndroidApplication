package com.example.firstandroidapplication.dto

class EventPost(
    id: Long,
    type: PostType = PostType.EVENT,
    author: String,
    content: String,
    created: Int,
    comments: Comments,
    likes: Likes,
    reposts: Reposts/*,
    val geo: Geo */
) : Post(id, type, author, content, created, comments, likes, reposts)
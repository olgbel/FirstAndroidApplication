package dto

open class Post(val id: Long,
           val author: String,
           val content: String,
           val created: String,
           val comments: Comments,
           val likes: Likes,
           val reposts: Reposts
)
package dto

class Post(val id: Long,
           val author: String,
           val content: String,
           val created: String, // пока строка
           val comments: Comments,
           val likes: Likes,
           val reposts: Reposts
)
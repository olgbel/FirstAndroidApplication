package dto

class EventPost(
    id: Long,
    author: String,
    content: String,
    created: String,
    comments: Comments,
    likes: Likes,
    reposts: Reposts,
    val address: String,
    val geo: Geo
) : Post(id, author, content, created, comments, likes, reposts) {
}
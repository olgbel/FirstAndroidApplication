package dto

class EventPost(
    id: Long,
//    type: PostType = PostType.EVENT,
    author: String,
    content: String,
    created: Int,
    comments: Comments,
    likes: Likes,
    reposts: Reposts,
    val geo: Geo
) : Post(id, author, content, created, comments, likes, reposts)
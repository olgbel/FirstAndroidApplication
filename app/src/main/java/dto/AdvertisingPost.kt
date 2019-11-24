package dto

class AdvertisingPost(
    id: Long,
//    type: PostType = PostType.ADVERTISING,
    author: String,
    content: String,
    created: Int,
    comments: Comments,
    likes: Likes,
    reposts: Reposts,
    val url: String): Post(id, author, content, created, comments, likes, reposts)
package dto

class Likes(var count: Int = 0,
            var userLikes: Boolean = false,
            val canPublish: Boolean = true) {
}
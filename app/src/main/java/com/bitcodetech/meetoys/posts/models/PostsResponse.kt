package com.bitcodetech.meetoys.posts.models

import com.google.gson.annotations.SerializedName

class PostsResponse(
    @SerializedName("data")
    val posts : ArrayList<Post>
)
package com.bitcodetech.meetoys.myposts.model

import com.google.gson.annotations.SerializedName

data class MyPostResponse(
    @SerializedName("data")
    val my_posts : ArrayList<MyPost>
)

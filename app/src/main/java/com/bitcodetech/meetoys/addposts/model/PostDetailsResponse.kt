package com.bitcodetech.meetoys.addposts.model

import com.google.gson.annotations.SerializedName

data class PostDetailsResponse(
    @SerializedName("data")
    val postDetailsModel : ArrayList<AddPostModel>
){
}
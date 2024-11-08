package com.bitcodetech.meetoys.addposts.model

data class AddPostModel(
    val postImage :Int,
    val name : String,
    val price : String,
    val description : String,
    val condition : String,
    val category : String
)
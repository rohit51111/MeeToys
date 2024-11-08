package com.bitcodetech.meetoys.ourposts.models

import java.io.Serializable

data class OurPost(

    val id : Int,
    val title:String,
    val price:String,
    val description: String,
    val condition:String,
    val created_at:String,
    /*val images_url:Int*/

): Serializable

package com.bitcodetech.meetoys.myposts.model

import java.io.Serializable

data class MyPost(

    val id : Int,
    val category_id:Int,
    val title:String,
    val price:String,
    val description: String,
    val condition:String,
    val users_id:Int,
    val status:Int,
    val created_at:String,
    val icon_url:String,
    val post_id:Int,
    val images_url:Int



): Serializable



/*val id : Int,
   val title:String,
   val price:String,
   val description: String,
   val condition:String,
   val created_at:String,
   val images_url:Int*/

package com.bitcodetech.meetoys.auth.register.models

data class UserPostModel(
    val id :String,
    val user_name : String,
    val email : String,
    val password : String,
    val mobile_number :String,
    val address : String

)

package com.bitcodetech.meetoys.auth.register.models

data class UserRegistration(

    val user_name : String,
    val email : String,
    val password : String,
    val  id:String,
    val mobile_number :String,
    val address : String
) {
}
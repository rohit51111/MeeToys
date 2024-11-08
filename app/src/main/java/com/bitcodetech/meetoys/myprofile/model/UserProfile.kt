package com.bitcodetech.meetoys.myprofile.model

import java.io.Serializable


data class UserProfile (

    val id :Int,
    val user_name : String,
    val email : String,
    val mobile_number : String,
    val address : String


): Serializable

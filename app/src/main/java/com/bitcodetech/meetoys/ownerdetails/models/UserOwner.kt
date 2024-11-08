package com.bitcodetech.meetoys.ownerdetails.models

import java.io.Serializable

data class UserOwner(
    val id :Int,
    val user_name : String,
    val email : String,
    val mobile_number : String,
    val address : String
): Serializable

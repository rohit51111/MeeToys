package com.bitcodetech.meetoys.EditProfile.Model

import com.bitcodetech.meetoys.commons.repository.Repository

data class EditProfileModel(

    val user_name: String,
    val email: String,
    //val password : String,
    val mobile_number: String,
    val address: String

)

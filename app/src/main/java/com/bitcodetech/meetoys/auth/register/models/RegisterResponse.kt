package com.bitcodetech.meetoys.auth.register.models
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    val userRegistration : UserRegistration
)



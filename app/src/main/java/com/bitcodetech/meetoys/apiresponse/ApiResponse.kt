package com.bitcodetech.meetoys.apiresponse

import com.google.gson.annotations.SerializedName

sealed class ApiResponse {
    @SerializedName("response_code")
    var responseCode : Int = -1
    @SerializedName("response_message")
    var responseMessage : String = ""


    class Success<T> : ApiResponse() {
        var data : T? = null
    }

    class Failure(val message : String) : ApiResponse()
}
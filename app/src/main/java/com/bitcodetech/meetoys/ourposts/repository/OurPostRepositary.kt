package com.bitcodetech.meetoys.ourposts.repository

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.ourposts.models.OurPost
import com.bitcodetech.meetoys.ourposts.network.OurPostApiService
import com.bitcodetech.meetoys.commons.repository.Repository

class OurPostRepositary(private val ourPostApiService: OurPostApiService): Repository() {

    suspend fun fetchourPost(): ArrayList<OurPost> {
        return ourPostApiService.ourposts()
    }


    suspend fun deletePost(): ApiResponse {
        return try {
            ourPostApiService.deleteMyPost()

        } catch (e: Exception){
            ApiResponse.Failure("Something Went Wrong")
        }
    }


}





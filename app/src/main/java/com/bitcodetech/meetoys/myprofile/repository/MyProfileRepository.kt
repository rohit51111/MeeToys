package com.bitcodetech.meetoys.myprofile.repository

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.auth.login.models.LoginResponse
import com.bitcodetech.meetoys.commons.repository.Repository
import com.bitcodetech.meetoys.myprofile.model.UserProfile
import com.bitcodetech.meetoys.myprofile.network.MyProfileApiService

class MyProfileRepository(private val myProfileApiService: MyProfileApiService
) : Repository() {

    suspend fun myProfile() : ApiResponse.Success<UserProfile> {
        return myProfileApiService.myProfile()
    }



    suspend fun deleteProfile():ApiResponse{
        return try {
            myProfileApiService.deleteProfile()
        } catch (e: Exception){
            ApiResponse.Failure("Something went Wrong")
        }
    }




}

package com.bitcodetech.meetoys.ownerdetails.repository

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.commons.repository.Repository
import com.bitcodetech.meetoys.ownerdetails.Network.OwnerApiService
import com.bitcodetech.meetoys.ownerdetails.models.UserOwner

class OwnerDetailsRepository(
    private val ownerApiService: OwnerApiService
) : Repository() {
    suspend fun ownerDetails() : ApiResponse.Success<UserOwner> {
        return ownerApiService.ownerDetails()
    }
}
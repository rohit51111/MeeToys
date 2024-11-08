package com.bitcodetech.meetoys.EditProfile.Repositary

import com.bitcodetech.meetoys.EditProfile.Model.EditProfileModel
import com.bitcodetech.meetoys.EditProfile.Model.EditResponse
import com.bitcodetech.meetoys.EditProfile.Network.EditApiService
import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.commons.repository.Repository

class EditRepository (private val editApiService: EditApiService) : Repository() {

    suspend fun updateProfile(editProfileModel: EditProfileModel) : ApiResponse {
        return try {
            editApiService.editMyProfile(editProfileModel)
        } catch (e : Exception) {

            ApiResponse.Failure("Something went wrong")
        }
    }

}
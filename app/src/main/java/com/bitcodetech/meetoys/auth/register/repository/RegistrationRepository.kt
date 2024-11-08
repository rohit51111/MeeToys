package com.bitcodetech.meetoys.auth.register.repository

import com.bitcodetech.meetoys.auth.register.models.RegisterResponse
import com.bitcodetech.meetoys.auth.register.models.UserPostModel
import com.bitcodetech.meetoys.auth.register.network.RegisterApiService
import com.bitcodetech.meetoys.commons.repository.Repository

class RegistrationRepository(
    private val registerApiService: RegisterApiService
)  : Repository() {
    suspend fun addUser (
        userPostModel: UserPostModel
    ): RegisterResponse{
        return registerApiService.addUser(userPostModel)
    }
}
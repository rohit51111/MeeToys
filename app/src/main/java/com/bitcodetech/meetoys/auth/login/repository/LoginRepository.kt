package com.bitcodetech.meetoys.auth.login.repository

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.auth.login.models.Credentials
import com.bitcodetech.meetoys.auth.login.models.LoginResponse
import com.bitcodetech.meetoys.auth.login.network.LoginApiService
import com.bitcodetech.meetoys.commons.repository.Repository

class LoginRepository(private val loginApiService: LoginApiService) : Repository() {
   /* fun validateCredentials(
        credentials: Credentials
    ) : Boolean {
        return true
    }*/
   suspend fun login(credentials: Credentials) : ApiResponse.Success<LoginResponse> {
       return loginApiService.login(credentials)
   }
    //fetch('https://dummyjson.com/auth/login
}
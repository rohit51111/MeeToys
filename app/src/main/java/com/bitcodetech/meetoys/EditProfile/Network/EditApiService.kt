package com.bitcodetech.meetoys.EditProfile.Network

import com.bitcodetech.meetoys.EditProfile.Model.EditProfileModel
import com.bitcodetech.meetoys.EditProfile.Model.EditResponse
import com.bitcodetech.meetoys.apiresponse.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.PUT

interface EditApiService {

    @PUT("users/61")
    suspend fun editMyProfile(
        @Body editProfileModel: EditProfileModel): ApiResponse.Success<EditProfileModel>

    companion object {
        private var editApiService : EditApiService? = null
        fun getInstance() : EditApiService {
            if(editApiService == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor{
                            chain ->
                        val requestBuilder: Request.Builder = chain.request().newBuilder()
                        requestBuilder.header("Content-Type","application/json")
                        requestBuilder.addHeader("Authorization",
                         //   "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjUsImlhdCI6MTcwNjY5Nzg3MH0.hTcq23YtfIB3GxzrU3bOQsmJuS4uMElim9Y6z5Ti9Nc")
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjU0LCJpYXQiOjE3MDk4MTE3NzQsImV4cCI6MTcwOTg5ODE3NH0.MiLUEuTEcB23wDhD4zTCqFGfMqqjZeh6VqHblZj6WCg"
                        )
                        chain.proceed(requestBuilder.build())
                    }
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.209.79:3000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                editApiService = retrofit.create(EditApiService::class.java)
            }
            return editApiService!!





        }
    }
}
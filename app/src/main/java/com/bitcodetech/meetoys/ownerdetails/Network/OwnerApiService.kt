package com.bitcodetech.meetoys.ownerdetails.Network

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.ownerdetails.models.UserOwner
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface OwnerApiService {
    @GET("53")
    suspend fun ownerDetails(): ApiResponse.Success<UserOwner>
    companion object {

        private var ownerApiService: OwnerApiService? = null

        fun getInstance(): OwnerApiService {
            if (ownerApiService == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor { chain ->
                        val requestBuilder: Request.Builder = chain.request().newBuilder()
                        requestBuilder.header("Content-Type", "application/json")
                        requestBuilder.addHeader(
                            "Authorization",
                            //"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjUsImlhdCI6MTcwNjY5Nzg3MH0.hTcq23YtfIB3GxzrU3bOQsmJuS4uMElim9Y6z5Ti9Nc"
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjU0LCJpYXQiOjE3MDk4MTE3NzQsImV4cCI6MTcwOTg5ODE3NH0.MiLUEuTEcB23wDhD4zTCqFGfMqqjZeh6VqHblZj6WCg"

                        )
                        chain.proceed(requestBuilder.build())
                    }
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.209.79:3000/api/users/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                ownerApiService = retrofit.create(OwnerApiService::class.java)
            }
            return ownerApiService!!

        }
    }
}
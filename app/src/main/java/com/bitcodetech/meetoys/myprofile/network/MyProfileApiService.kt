package com.bitcodetech.meetoys.myprofile.network

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.myprofile.model.DeleteMyProfileResponse
import com.bitcodetech.meetoys.myprofile.model.UserProfile
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface MyProfileApiService {

    @GET("53")
    suspend fun myProfile(): ApiResponse.Success<UserProfile>


    @DELETE("53")
    suspend fun deleteProfile(): ApiResponse.Success<DeleteMyProfileResponse>


   /* @DELETE("users/{postId}")
    suspend fun deletePost(@Path("postId") postId: String): Call<ApiResponse>*/


    /*@DELETE("users/{61}")
    suspend fun deletePost(@Path("61") postId: String): Call<ApiResponse>*/

    companion object {
        private var myProfileApiService: MyProfileApiService? = null

        fun getInstance(): MyProfileApiService {
            if (myProfileApiService == null) {


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
                    //.baseUrl("http://ec2-13-232-219-171.ap-south-1.compute.amazonaws.com:3000/api/users/")
                    .baseUrl("http://192.168.209.79:3000/api/users/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                myProfileApiService = retrofit.create(MyProfileApiService::class.java)
            }
            return myProfileApiService!!

        }
    }

}

package com.bitcodetech.meetoys.addposts.network

import com.bitcodetech.meetoys.addposts.model.AddPostModel
import com.bitcodetech.meetoys.addposts.model.PostDetailsResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AddPostApiService {

    @POST("all_data")
    suspend fun addPosts(
        @Body addPostModel: AddPostModel
    ) : PostDetailsResponse

    companion object {
        private var addpostApiService: AddPostApiService? = null

        fun getInstance() : AddPostApiService {
            if(addpostApiService == null) {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor{
                            chain ->
                        val requestBuilder: Request.Builder = chain.request().newBuilder()
                        requestBuilder.header("Content-Type","application/json")
                        requestBuilder.addHeader("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjU0LCJpYXQiOjE3MDk4MTE3NzQsImV4cCI6MTcwOTg5ODE3NH0.MiLUEuTEcB23wDhD4zTCqFGfMqqjZeh6VqHblZj6WCg")
                        chain.proceed(requestBuilder.build())
                    }
                    .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.43.95:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            addpostApiService = retrofit.create(AddPostApiService::class.java)
            }
         return addpostApiService!!
        }
    }
}
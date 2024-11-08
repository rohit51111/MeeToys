package com.bitcodetech.meetoys.ourposts.network

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.myposts.model.DeleteMyPostResponse
import com.bitcodetech.meetoys.ourposts.models.OurPost

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET

interface OurPostApiService {


    @GET("61/posts") suspend fun ourposts() : ArrayList<OurPost>

    @DELETE("41")
    suspend fun deleteMyPost() : ApiResponse.Success<DeleteMyPostResponse>


    companion object {
        private var ourPostApiService : OurPostApiService? = null

        fun getInstance() : OurPostApiService {
            if(ourPostApiService == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.209.79:3000/all_data/user/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                ourPostApiService = retrofit.create(OurPostApiService::class.java)
            }
            return ourPostApiService!!
        }
    }
}
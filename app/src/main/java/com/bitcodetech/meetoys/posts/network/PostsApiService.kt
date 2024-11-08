package com.bitcodetech.meetoys.posts.network


import com.bitcodetech.meetoys.posts.models.Post
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostsApiService {

    @GET("all_data") suspend fun posts() : ArrayList<Post>

    companion object {
        private var postsApiService : PostsApiService? = null

        fun getInstance() : PostsApiService {
            if(postsApiService == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.209.79:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                postsApiService = retrofit.create(PostsApiService::class.java)
            }
            return postsApiService!!
        }
    }
}
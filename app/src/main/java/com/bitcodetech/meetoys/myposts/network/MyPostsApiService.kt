package com.bitcodetech.meetoys.myposts.network
import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.myposts.model.DeleteMyPostResponse
import com.bitcodetech.meetoys.myposts.model.MyPostResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET

interface MyPostsApiService {


    @GET("all_data")
    suspend fun myPosts() : MyPostResponse

    @DELETE("all_data")
    suspend fun deleteMyPost() : ApiResponse.Success<DeleteMyPostResponse>


    companion object {
        private var myPostsApiService : MyPostsApiService? = null

        fun getInstance() : MyPostsApiService {
            if(myPostsApiService == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor{
                            chain ->
                        val requestBuilder: Request.Builder = chain.request().newBuilder()
                        requestBuilder.header("Content-Type","application/json")
                        requestBuilder.addHeader("Authorization",
                            //"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjUsImlhdCI6MTcwNjY5Nzg3MH0.hTcq23YtfIB3GxzrU3bOQsmJuS4uMElim9Y6z5Ti9Nc"
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjU0LCJpYXQiOjE3MDk4MTE3NzQsImV4cCI6MTcwOTg5ODE3NH0.MiLUEuTEcB23wDhD4zTCqFGfMqqjZeh6VqHblZj6WCg"

                        )
                        chain.proceed(requestBuilder.build())
                    }
                    .build()


                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.209.79:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                myPostsApiService = retrofit.create(MyPostsApiService::class.java)
            }
            return myPostsApiService!!



           //      http://localhost:3000/all_data/user/61/posts

        }
    }
}
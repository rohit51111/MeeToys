package com.bitcodetech.meetoys.auth.login.network

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.auth.login.models.Credentials
import com.bitcodetech.meetoys.auth.login.models.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("login")
    suspend fun login(
        @Body credentials: Credentials
    ) : ApiResponse.Success<LoginResponse>

    companion object{
        private var loginApiService : LoginApiService? = null
        fun getInstance() : LoginApiService {
            if (loginApiService == null){

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    //.baseUrl("http://ec2-13-232-219-171.ap-south-1.compute.amazonaws.com:5000/api/users/")
                    .baseUrl("http://192.168.209.79:3000/api/users/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                loginApiService = retrofit.create(LoginApiService::class.java)
            }
            return loginApiService!!

        }
    }
}
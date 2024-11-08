package com.bitcodetech.meetoys.auth.register.network

import com.bitcodetech.meetoys.auth.login.models.Credentials
import com.bitcodetech.meetoys.auth.register.models.RegisterResponse
import com.bitcodetech.meetoys.auth.register.models.UserPostModel
import com.bitcodetech.meetoys.auth.register.viewmodel.RegistrationViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {

     @POST("register")
        suspend fun addUser(
            @Body registrationViewModel: UserPostModel
        ) : RegisterResponse


        companion object {
            private var registerApiService : RegisterApiService? = null

            fun getInstance() : RegisterApiService {
                if(registerApiService == null) {

                    val interceptor = HttpLoggingInterceptor()
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                    val retrofit = Retrofit.Builder()
                        //.baseUrl("http://ec2-13-232-219-171.ap-south-1.compute.amazonaws.com:5000/api/users/")
                        .baseUrl("http://192.168.209.79:3000/api/users/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()

                    registerApiService = retrofit.create(RegisterApiService::class.java)
                }
                return registerApiService!!
            }
        }

}
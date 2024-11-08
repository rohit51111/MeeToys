package com.bitcodetech.meetoys.categories.network

import com.bitcodetech.meetoys.auth.register.models.UserPostModel
import com.bitcodetech.meetoys.categories.models.Categories
import com.bitcodetech.meetoys.categories.models.CategoryResponse
import com.bitcodetech.meetoys.categories.viewModel.CategoryViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryApiService {

    @POST("insert")
    suspend fun fetchCategory(
        @Body categoryViewModel: Categories
    ): CategoryResponse

    companion object {
        private var categoryApiService: CategoryApiService? = null

        fun getInstance(): CategoryApiService {
            if (categoryApiService == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                val client =  OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();


                val retrofit = Retrofit.Builder()
                    .baseUrl("http://ec2-13-232-219-171.ap-south-1.compute.amazonaws.com:5000/api/categories/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                categoryApiService = retrofit.create(CategoryApiService::class.java)
            }
            return categoryApiService!!

           // "http://ec2-13-232-219-171.ap-south-1.compute.amazonaws.com:5000/api/categories/insert"

        }
    }
}
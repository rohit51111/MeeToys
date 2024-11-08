package com.bitcodetech.meetoys.myposts.repository

import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.commons.repository.Repository
import com.bitcodetech.meetoys.myposts.model.MyPost
import com.bitcodetech.meetoys.myposts.network.MyPostsApiService


class MyPostRepository(private val myPostsApiService: MyPostsApiService) : Repository() {


    suspend fun fetchMyPosts() : ArrayList<MyPost> {
        return myPostsApiService.myPosts().my_posts
    }

    suspend fun deletePost(): ApiResponse{
        return try {
            myPostsApiService.deleteMyPost()

        } catch (e: Exception){
            ApiResponse.Failure("Something Went Wrong")
        }
    }
}
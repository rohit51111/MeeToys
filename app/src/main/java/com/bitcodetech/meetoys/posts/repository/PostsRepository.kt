package com.bitcodetech.meetoys.posts.repository

import com.bitcodetech.meetoys.posts.models.Post
import com.bitcodetech.meetoys.commons.repository.Repository
import com.bitcodetech.meetoys.posts.network.PostsApiService

class PostsRepository(private val postsApiService: PostsApiService) : Repository() {
    suspend fun fetchPosts() : ArrayList<Post> {
        return postsApiService.posts()
    }
}
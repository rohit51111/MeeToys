package com.bitcodetech.meetoys.addposts.repository

import com.bitcodetech.meetoys.addposts.model.AddPostModel
import com.bitcodetech.meetoys.addposts.model.PostDetailsResponse
import com.bitcodetech.meetoys.addposts.network.AddPostApiService
import com.bitcodetech.meetoys.commons.repository.Repository


class AddPostRepository(
    private val addpostApiService: AddPostApiService
) : Repository() {
    suspend fun addPost(
             addpostmodel : AddPostModel
            ):PostDetailsResponse{

            return addpostApiService.addPosts(addpostmodel)
    }
}
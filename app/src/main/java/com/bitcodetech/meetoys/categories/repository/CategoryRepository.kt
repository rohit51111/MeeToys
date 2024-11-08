package com.bitcodetech.meetoys.categories.repository

import com.bitcodetech.meetoys.auth.register.models.RegisterResponse
import com.bitcodetech.meetoys.auth.register.models.UserPostModel
import com.bitcodetech.meetoys.auth.register.network.RegisterApiService
import com.bitcodetech.meetoys.categories.models.Categories
import com.bitcodetech.meetoys.categories.models.CategoryResponse
import com.bitcodetech.meetoys.categories.network.CategoryApiService
import com.bitcodetech.meetoys.commons.repository.Repository

class CategoryRepository(
    private val categoryApiService: CategoryApiService)
    : Repository()  {

    suspend fun fetchCategory(
        categories: Categories
    ) : CategoryResponse {
        return categoryApiService.fetchCategory(categories)
    }
}

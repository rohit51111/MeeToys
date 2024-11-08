package com.bitcodetech.meetoys.categories.models

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
     val category: Categories
){
}
package com.bitcodetech.meetoys.ourposts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.ourposts.models.OurPost
import com.bitcodetech.meetoys.ourposts.repository.OurPostRepositary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OurPostViewModel(private val ourPostRepositary: OurPostRepositary): ViewModel() {


    val ourpostsMutableLiveData = MutableLiveData<Boolean>()
    val ourposts = ArrayList<OurPost>()


    fun ourfetchPosts() {
        CoroutineScope(Dispatchers.IO).launch {
            val ourposts = ourPostRepositary.fetchourPost()

            withContext(Dispatchers.Main){
                this@OurPostViewModel.ourposts.addAll(ourposts)
                ourpostsMutableLiveData.postValue(true)
            }
        }
    }



    val deletePostMutableLiveData = MutableLiveData<Boolean>()


    fun deletePost(){
        CoroutineScope(Dispatchers.IO).launch {
            val deletePostResponse : ApiResponse = ourPostRepositary.deletePost()
            when(deletePostResponse){
                is ApiResponse.Success<*> -> {
                    deletePostMutableLiveData.postValue(true)
                }
                is ApiResponse.Failure -> {
                    deletePostMutableLiveData.postValue(false)
                }
            }
        }
    }


}
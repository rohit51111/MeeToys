package com.bitcodetech.meetoys.myposts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.myposts.model.MyPost
import com.bitcodetech.meetoys.myposts.repository.MyPostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyPostViewModel(private val myPostRepository: MyPostRepository)  : ViewModel() {

    val myPostsMutableLiveData = MutableLiveData<Boolean>()
    val myPosts = ArrayList<MyPost>()


    fun fetchPosts() {
        CoroutineScope(Dispatchers.IO).launch {
            val myPosts = myPostRepository.fetchMyPosts()
            //Log.e("tag",myPost.toString())

            withContext(Dispatchers.Main) {
                this@MyPostViewModel.myPosts.addAll(myPosts)
                myPostsMutableLiveData.postValue(true)
            }
        }
    }



    val deletePostMutableLiveData = MutableLiveData<Boolean>()


    fun deletePost(){
        CoroutineScope(Dispatchers.IO).launch {
            val deletePostResponse : ApiResponse = myPostRepository.deletePost()
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


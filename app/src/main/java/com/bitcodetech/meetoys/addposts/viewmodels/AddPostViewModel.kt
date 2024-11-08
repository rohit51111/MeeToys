package com.bitcodetech.meetoys.addposts.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.addposts.model.AddPostModel
import com.bitcodetech.meetoys.addposts.repository.AddPostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPostViewModel(private val addPostRepository: AddPostRepository
) : ViewModel() {
    val addPostMutableLiveData = MutableLiveData<Boolean>()

    fun addPost(addPostModel: AddPostModel){
        CoroutineScope(Dispatchers.IO).launch {
            val response = addPostRepository.addPost(addPostModel)
            Log.e("tag",response.toString())

            withContext(Dispatchers.Main){
                addPostMutableLiveData.postValue(true)
            }
        }
    }
}
package com.bitcodetech.meetoys.EditProfile.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.EditProfile.Model.EditProfileModel
import com.bitcodetech.meetoys.EditProfile.Repositary.EditRepository
import com.bitcodetech.meetoys.apiresponse.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EditViewModel(private val editRepository: EditRepository) : ViewModel() {

    val editPostsMutableLiveData = MutableLiveData<Boolean>()
    //val editPosts = ArrayList<EditProfile>()

    fun updateProfile(editProfileModel: EditProfileModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val editProfileResponse = editRepository.updateProfile(editProfileModel)

            when{
                editProfileResponse is ApiResponse.Success<*> -> {
                    editPostsMutableLiveData.postValue(true)
                }
                editProfileResponse is ApiResponse.Failure -> {
                    editPostsMutableLiveData.postValue(false)
                }
            }

            withContext(Dispatchers.Main) {
                editPostsMutableLiveData.postValue(true)
            }
        }
    }








}
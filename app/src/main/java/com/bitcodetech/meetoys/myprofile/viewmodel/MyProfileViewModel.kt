package com.bitcodetech.meetoys.myprofile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.apiresponse.ApiResponse
import com.bitcodetech.meetoys.myprofile.model.UserProfile
import com.bitcodetech.meetoys.myprofile.repository.MyProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyProfileViewModel(
    private val myProfileRepository: MyProfileRepository
): ViewModel(){

    val myProfileLiveData = MutableLiveData<UserProfile>()

    fun myProfile() {
        CoroutineScope(Dispatchers.IO).launch {
            val userResponse = myProfileRepository.myProfile()
            Log.e("tag", userResponse.toString())
            withContext(Dispatchers.Main) {
                myProfileLiveData.postValue(userResponse.data)
            }
        }
    }


    val deleteProfileMutableLiveData = MutableLiveData<Boolean>()

    fun deleteProfile(){
       CoroutineScope(Dispatchers.IO).launch {
           val deleteProfileResponse : ApiResponse = myProfileRepository.deleteProfile()


           when(deleteProfileResponse){
               is ApiResponse.Success<*> -> {
                   deleteProfileMutableLiveData.postValue(true)
               }

               is ApiResponse.Failure ->{
                   deleteProfileMutableLiveData.postValue(false)
               }

               else ->{}
           }

       }
    }





    /*val deletePostLiveData = MutableLiveData<ApiResponse>()*/

}
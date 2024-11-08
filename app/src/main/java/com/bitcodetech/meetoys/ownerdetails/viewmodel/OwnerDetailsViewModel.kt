package com.bitcodetech.meetoys.ownerdetails.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.ownerdetails.models.UserOwner
import com.bitcodetech.meetoys.ownerdetails.repository.OwnerDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OwnerDetailsViewModel(
    private val ownerDetailsRepository: OwnerDetailsRepository
) : ViewModel() {

    val ownerLiveData = MutableLiveData<UserOwner>()

    fun myProfile() {
        CoroutineScope(Dispatchers.IO).launch {
            val userResponse = ownerDetailsRepository.ownerDetails()
            Log.e("tag", userResponse.toString())
            withContext(Dispatchers.Main) {
                ownerLiveData.postValue(userResponse.data)
            }
        }
    }
}
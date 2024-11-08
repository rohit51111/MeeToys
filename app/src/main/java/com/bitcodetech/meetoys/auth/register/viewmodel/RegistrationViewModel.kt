package com.bitcodetech.meetoys.auth.register.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.auth.register.models.UserPostModel
import com.bitcodetech.meetoys.auth.register.repository.RegistrationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(
    private val registrationRepository: RegistrationRepository
)  : ViewModel() {

    val registrationMutableLiveData = MutableLiveData<Boolean>()
    fun addUser(
        userPostModel: UserPostModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val users = registrationRepository.addUser(userPostModel)
            Log.e("tag",users.toString())

            withContext(Dispatchers.Main) {
                registrationMutableLiveData.postValue(true)
            }
        }
    }
}
package com.bitcodetech.meetoys.auth.login.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.auth.login.models.Credentials
import com.bitcodetech.meetoys.auth.login.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val userLoginStatusLiveData = MutableLiveData<Boolean>()

    private var token:String? = null
    fun getToken():String {
        return token ?: ""
    }

    fun validateCredentials(
        email : String,
        password : String
    ){
        CoroutineScope(Dispatchers.IO).launch {
            try{
             val response = loginRepository.login(
                 Credentials(email, password)
             )

            withContext(Dispatchers.Main){
                userLoginStatusLiveData.postValue(true)
            }
        } catch (e : Exception) {
                withContext(Dispatchers.Main) {
                    userLoginStatusLiveData.postValue(false)
                }
            }
        }
    }


}
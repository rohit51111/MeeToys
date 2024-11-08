package com.bitcodetech.meetoys.auth.register.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.auth.login.activity.LoginActivity
import com.bitcodetech.meetoys.auth.register.models.UserPostModel
import com.bitcodetech.meetoys.auth.register.network.RegisterApiService
import com.bitcodetech.meetoys.auth.register.repository.RegistrationRepository
import com.bitcodetech.meetoys.auth.register.viewmodel.RegistrationViewModel
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.RegisterActivityBinding

class   RegisterActivity  :AppCompatActivity() {

    private lateinit var binding: RegisterActivityBinding
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        initViews()
        initModels()
        initObserver()
        initListeners()
    }
    private fun initListeners(){
        binding.registerBtn.setOnClickListener {
            //val userIdString = binding.id.text.toString()
            registrationViewModel.addUser(
               UserPostModel(
                   binding.id.text.toString(),
                   binding.fullName.text.toString(),
                   binding.Email.text.toString(),
                   binding.password.text.toString(),
                   binding.phone.text.toString(),
                   binding.address.text.toString())
            )
        }
    }
    private fun startMainActivity() {
        startActivity(
            Intent(this@RegisterActivity, LoginActivity::class.java)
        )
    }

    private fun initObserver() {
        registrationViewModel.registrationMutableLiveData.observe(
            this
        ) {
            if (it) {
                finish()
                startMainActivity()
            }
        }
    }

    private fun initModels() {
        registrationViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                RegistrationRepository(
                    RegisterApiService.getInstance()
                )
            )
        )[RegistrationViewModel::class.java]
    }
    private fun initViews(){
        binding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

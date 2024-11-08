package com.bitcodetech.meetoys.auth.login.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.MainActivity
import com.bitcodetech.meetoys.auth.login.network.LoginApiService
import com.bitcodetech.meetoys.auth.login.repository.LoginRepository
import com.bitcodetech.meetoys.auth.login.viewmodel.LoginViewModel
import com.bitcodetech.meetoys.auth.register.activity.RegisterActivity
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences :SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        initViews()
        initListener()
        initViewModels()
        initObserver()
        sharedPreferences = getSharedPreferences("MyPrefs",Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token",null)
        if (token != null){
            startMainActivity()
            finish()
        }
    }
    private fun initObserver(){
        loginViewModel.userLoginStatusLiveData.observe(
            this
        ){
            if(it) {
                val token = loginViewModel.getToken()
                saveTokenToSharedPreferences(token)
                finish()
                startMainActivity()
            }
            else {
                showInvalidCredentials()
            }
        }
    }
    private fun showInvalidCredentials(){
        runOnUiThread{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Invalid Data")
                .setMessage("Invalid Username or Password")
                .setPositiveButton("Done"){ dialog, _ ->
                    dialog.dismiss()
                }
            val dialog = builder.create()
            dialog.show()
        }
    }
    private fun saveTokenToSharedPreferences(token:String){
        val editor = sharedPreferences.edit()
        editor.putString("token",token)
        editor.apply()
    }

    private fun startMainActivity() {
        startActivity(
            Intent(this@LoginActivity, MainActivity::class.java)
        )
    }
    private fun initViewModels(){
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                LoginRepository(
                    LoginApiService.getInstance()
                )
            )
        )[LoginViewModel::class.java]
    }
    private fun initListener(){
        binding.btnLogin.setOnClickListener {

             val username = binding.edtUserName.text.toString()
             val password = binding.edtPassword.text.toString()

            if (username.isEmpty() && password.isEmpty()){
                binding.abc.text="Enter Valid Username"
                binding.Enter.text="Enter Valid Password"
            }
            if (username.isEmpty()){
                binding.abc.text="Enter Valid Username"
            }
            if (password.isEmpty()) {
                binding.Enter.text = "Enter Valid Password"
            }

            loginViewModel.validateCredentials(username, password)


        }
        binding.txtRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
            //Toast.makeText(this,"text clicked",Toast.LENGTH_SHORT).show()
        }
    }
    private fun initViews(){
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
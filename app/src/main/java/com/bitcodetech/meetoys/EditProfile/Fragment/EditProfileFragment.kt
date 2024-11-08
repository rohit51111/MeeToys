package com.bitcodetech.meetoys.EditProfile.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.EditProfile.Model.EditProfileModel
import com.bitcodetech.meetoys.EditProfile.Network.EditApiService
import com.bitcodetech.meetoys.EditProfile.Repositary.EditRepository
import com.bitcodetech.meetoys.EditProfile.ViewModel.EditViewModel
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.FragmentEditProfileBinding
import com.bitcodetech.meetoys.myprofile.fragments.MyProfileFragment
import com.bitcodetech.meetoys.myprofile.model.UserProfile

class EditProfileFragment : Fragment() {

    private lateinit var binding  : FragmentEditProfileBinding
    private lateinit var editViewModel: EditViewModel
    private var userProfile : UserProfile? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentEditProfileBinding.inflate(layoutInflater)

        editProfile()
        initListener()
        initViewModels()
        initObserver()

        binding.root.setOnClickListener {
        }
        setHasOptionsMenu(true)
        return binding.root


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun editProfile(){
        if(arguments != null){
            userProfile = requireArguments().getSerializable("profile") as UserProfile


            binding.edtName.setText(userProfile?.user_name ?: "")
          //  binding.edtpassword.setText(userProfile?.user_name.toString() ?: "")
            binding.edtemail.setText(userProfile?.email ?: "")
            binding.edtMobileNo.setText(userProfile?.mobile_number ?: "")
            binding.edtLocation.setText(userProfile?.address ?: "")

        }

    }
    private fun initListener(){

        binding.btnUpdateProfile.setOnClickListener {
            editViewModel.updateProfile(
                EditProfileModel
                    (
                    binding.edtName.text.toString(),
                   // binding.edtpassword.text.toString(),
                    binding.edtemail.text.toString(),
                    binding.edtMobileNo.text.toString(),
                    binding.edtLocation.text.toString(),
                )
            )
            Toast.makeText(context,"Profile Updated",Toast.LENGTH_SHORT).show()

            val myProfileFragment = MyProfileFragment()
            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, myProfileFragment, null)
                .addToBackStack(null)
                .commit()
        }
    }
    private fun initObserver()
    {
        editViewModel.editPostsMutableLiveData.observe(
            viewLifecycleOwner
        ){
            parentFragmentManager.popBackStack()
        }
    }
    private fun initViewModels(){
        editViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                EditRepository(EditApiService.getInstance())
            )
        )[EditViewModel::class.java]
    }


}

// change model class & check EditResponse
// change url of Api service
// do work in initlistner & editProfile of password of this file


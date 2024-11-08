package com.bitcodetech.meetoys.myprofile.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.EditProfile.Fragment.EditProfileFragment
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.auth.login.activity.LoginActivity
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.myprofile.model.UserProfile
import com.bitcodetech.meetoys.databinding.ProfileFragmentBinding
import com.bitcodetech.meetoys.myprofile.network.MyProfileApiService
import com.bitcodetech.meetoys.myprofile.repository.MyProfileRepository
import com.bitcodetech.meetoys.myprofile.viewmodel.MyProfileViewModel
import com.bitcodetech.meetoys.ourposts.fragment.OurPostFragment
import com.bitcodetech.meetoys.posts.fragment.PostsFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var myProfileViewModel: MyProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        binding.root.setOnClickListener { }

        initViewModels()
        initObserver()

        setHasOptionsMenu(true)
        myProfileViewModel.myProfile()

        binding.root.setOnClickListener {  }
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.my_profile_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.editProfile -> {

                val editProfileFragment = EditProfileFragment()
                val bundle = Bundle()
                bundle.putSerializable("profile", myProfileViewModel.myProfileLiveData.value)
                editProfileFragment.arguments = bundle


                parentFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, editProfileFragment, null)
                    .addToBackStack(null)
                    .commit()

                return true
            }


            R.id.deleteProfile -> {
                when (item.itemId) {
                    R.id.deleteProfile -> {


                        val builder = AlertDialog.Builder(requireContext())
                        builder.setTitle("Delete Profile")
                        builder.setMessage("Are you sure you want to delete your profile?")

                        builder.setPositiveButton("Yes") { dialog, which ->
                            // Delete the profile
                            deleteProfile()
                        }

                        builder.setNegativeButton("No") { dialog, which ->
                            // Do nothing
                        }

                        val dialog = builder.create()
                        dialog.show()
                    }
                }
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }



    private fun deleteProfile(){
        myProfileViewModel.deleteProfileMutableLiveData.observe(
            this
        ){
            if (it){

                removeCurrentFragment()
                val intent = Intent(requireContext(),LoginActivity::class.java)
                startActivity(intent)

                val ourPostFragment = PostsFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer,ourPostFragment)
                    .addToBackStack(null)
                    .commit()

            }

            else{
                Snackbar.make(requireView(),"Unable to delete Profile !", Snackbar.LENGTH_SHORT).show()
            }
        }


        myProfileViewModel.deleteProfile()
    }



    private fun removeCurrentFragment(){
        parentFragmentManager.popBackStack()

    }




    private fun initObserver() {
        myProfileViewModel.myProfileLiveData.observe(
            viewLifecycleOwner
        ) {
            Toast.makeText(requireContext(), "Got the data", Toast.LENGTH_SHORT).show()
            binding.userProfile = it
        }
    }

    private fun initViewModels() {
        myProfileViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                MyProfileRepository(MyProfileApiService.getInstance())
            )
        )[MyProfileViewModel::class.java]
    }


}

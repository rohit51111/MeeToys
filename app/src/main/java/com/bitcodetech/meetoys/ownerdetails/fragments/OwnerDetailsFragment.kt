package com.bitcodetech.meetoys.ownerdetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.ownerdetails.repository.OwnerDetailsRepository
import com.bitcodetech.meetoys.ownerdetails.viewmodel.OwnerDetailsViewModel
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.OwnerDetailsFragmentBinding
import com.bitcodetech.meetoys.ownerdetails.Network.OwnerApiService

class OwnerDetailsFragment : Fragment() {


    private lateinit var binding: OwnerDetailsFragmentBinding
    private lateinit var myProfileViewModel: OwnerDetailsViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = OwnerDetailsFragmentBinding.inflate(layoutInflater)
        binding.root.setOnClickListener { }

        initViewModels()
        initObserver()

        setHasOptionsMenu(true)
        myProfileViewModel.myProfile()





        return binding.root

    }


    private fun initObserver() {
        myProfileViewModel.ownerLiveData.observe(
            viewLifecycleOwner
        ) {
            binding.userOwner = it

        }
    }

    private fun initViewModels() {
        myProfileViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                OwnerDetailsRepository(OwnerApiService.getInstance())
            )
        )[OwnerDetailsViewModel::class.java]
    }
}

// take api & Model Class
// change in .xml file also
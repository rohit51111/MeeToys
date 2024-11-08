package com.bitcodetech.meetoys.auth.aboutproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcodetech.meetoys.databinding.AboutProjectFragmentBinding


class AboutProject : Fragment() {

    private lateinit var binding: AboutProjectFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        binding = AboutProjectFragmentBinding.inflate(layoutInflater)
        binding.root.setOnClickListener{}
        initListener()
        return binding.root
    }

    private fun initListener(){
    }
}
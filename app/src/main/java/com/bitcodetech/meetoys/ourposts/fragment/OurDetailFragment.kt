package com.bitcodetech.meetoys.ourposts.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.ourposts.models.OurPost
import com.bitcodetech.meetoys.databinding.FragmentOurDetailBinding
import com.bitcodetech.meetoys.ourposts.network.OurPostApiService
import com.bitcodetech.meetoys.ourposts.repository.OurPostRepositary
import com.bitcodetech.meetoys.ourposts.viewmodel.OurPostViewModel


class OurDetailFragment : Fragment() {

    private lateinit var binding:FragmentOurDetailBinding
    private var ourPost : OurPost? = null
    // other code
    private lateinit var ourPostViewModel : OurPostViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       binding = FragmentOurDetailBinding.inflate(layoutInflater)


        initShowDetails()
        initViews()



        initViewModel()
        /* initListener()*/



        setHasOptionsMenu(true)

        binding.root.setOnClickListener {  }
        return binding.root
    }


    private fun initShowDetails() {
        if (arguments != null) {
            ourPost = requireArguments().getSerializable("posts") as OurPost
            Log.e("tag--", ourPost.toString())
            binding.ourposts = ourPost
        }
    }

    private fun initViews(){
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    // other code



    private fun initViewModel() {
        ourPostViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                OurPostRepositary(OurPostApiService.getInstance())
            )
        )[OurPostViewModel::class.java]
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.our_post_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.deleteMyPost -> {

                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Are you sure you want to delete this post?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        // Call a method to delete the post
                        deletePost()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deletePost(){
        ourPostViewModel.deletePost()

        Toast.makeText(context,"Post Deleted", Toast.LENGTH_SHORT).show()

        val ourPostFragment = OurPostFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, ourPostFragment)
            .addToBackStack(null)
            .commit()
    }



}
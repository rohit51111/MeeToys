package com.bitcodetech.meetoys.myposts.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.FragmentShowDetailsBinding
import com.bitcodetech.meetoys.myposts.model.MyPost
import com.bitcodetech.meetoys.myposts.network.MyPostsApiService
import com.bitcodetech.meetoys.myposts.repository.MyPostRepository
import com.bitcodetech.meetoys.myposts.viewmodel.MyPostViewModel

class ShowDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShowDetailsBinding
    private var myposts : MyPost? = null
    private lateinit var myPostViewModel : MyPostViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShowDetailsBinding.inflate(layoutInflater)
        initShowDetails()
        initViews()
        initViewModel()
        setHasOptionsMenu(true)
       /* initListener()*/
        binding.root.setOnClickListener {  }
        return binding.root

    }



    private fun initShowDetails() {
        if (arguments != null) {
            myposts = requireArguments().getSerializable("myposts") as MyPost
            Log.e("tag--", myposts.toString())
            binding.myposts = myposts
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* binding.btnContact.setOnClickListener {
            // Show BottomSheetDialog
            val dialog = BottomSheetDialog(requireContext())
            val bottomSheetView = layoutInflater.inflate(R.layout.owner_details_fragment, null)
            val btnClose = bottomSheetView.findViewById<Button>(R.id.btnDone)

            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(bottomSheetView)
            dialog.show()
        }*/
    }
    private fun initViews(){
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

/// from other code


    private fun initViewModel() {
        myPostViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                MyPostRepository(MyPostsApiService.getInstance())
            )
        )[MyPostViewModel::class.java]
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
        myPostViewModel.deletePost()

        Toast.makeText(context,"Post Deleted", Toast.LENGTH_SHORT).show()

        val myPostFragment = MyPostFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, myPostFragment)
            .addToBackStack(null)
            .commit()
    }
    /*private fun initListener(){
        binding.btnDone.setOnClickListener {

            val myPostFragment = MyPostFragment()
            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, myPostFragment, null)
                .addToBackStack(null)
                .commit()
        }
    }*/







}
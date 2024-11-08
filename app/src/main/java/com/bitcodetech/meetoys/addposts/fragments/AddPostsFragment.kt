package com.bitcodetech.meetoys.addposts.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.addposts.model.AddPostModel
import com.bitcodetech.meetoys.addposts.network.AddPostApiService
import com.bitcodetech.meetoys.addposts.repository.AddPostRepository
import com.bitcodetech.meetoys.addposts.viewmodels.AddPostViewModel
import com.bitcodetech.meetoys.posts.fragment.PostsFragment
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.AddPostsFragmentBinding
import com.bumptech.glide.Glide


class AddPostsFragment : Fragment() {

    private lateinit var binding: AddPostsFragmentBinding
    private lateinit var addPostViewModel: AddPostViewModel
    private var selectedImage: Int = 1
    private var selectedLocation: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = AddPostsFragmentBinding.inflate(layoutInflater)
        binding.root.setOnClickListener{}

        initViewModels()
        initObserver()
        initViewListeners()
        initSpinners()

        return binding.root
    }

    private fun initViewListeners() {

        binding.btnAddPosts.setOnClickListener {
            addPostViewModel.addPost(
                AddPostModel(
                    binding.upload.imageAlpha.toString().toInt(),
                    binding.name.text.toString(),
                    binding.price.text.toString(),
                    binding.description.text.toString(),
                    binding.condition.text.toString(),
                    binding.category.text.toString()
                ))

            val postsFragment = PostsFragment()
            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, postsFragment, null)
                .addToBackStack(null)
                .commit()
        }
        binding.upload.setOnClickListener{

            openImagePicker()
        }
    }


    private fun openImagePicker(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null){
            val selectImage :Uri? =data.data
            when{
                binding.upload.tag == null ->{
                    binding.upload.tag = selectImage
                    Glide.with(this)
                        .load(selectImage)
                        .into(binding.upload)
                }
            }
        }
    }
    private fun initObserver() {
        addPostViewModel.addPostMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initViewModels() {
        addPostViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                AddPostRepository(
                    AddPostApiService.getInstance()
                )
            )
        )[AddPostViewModel::class.java]
    }
    private fun initSpinners(){

        // Category Spinner
        val categories = arrayOf("Action figures 1", "Animals 2", " Cars and radio controlled 3","Construction toys 4","Others 5")
        val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnercategory.adapter = categoryAdapter

        binding.spinnercategory.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedcategory = categories [position]
                binding.category.text = selectedcategory
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context,"Plese Selected Age",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

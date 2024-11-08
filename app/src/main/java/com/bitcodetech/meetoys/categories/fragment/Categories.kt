package com.bitcodetech.meetoys.categories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.addposts.fragments.AddPostsFragment
import com.bitcodetech.meetoys.categories.models.Categories
import com.bitcodetech.meetoys.categories.network.CategoryApiService
import com.bitcodetech.meetoys.categories.repository.CategoryRepository
import com.bitcodetech.meetoys.categories.viewModel.CategoryViewModel
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.ViewCategoriesBinding

class Categories:Fragment() {

    private lateinit var binding :ViewCategoriesBinding
    private lateinit var categoryViewModel: CategoryViewModel
    //private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewCategoriesBinding.inflate(layoutInflater)
        binding.root.setOnClickListener {  }

        initModels()
        initObserver()
        initListeners()

    return binding.root
    }
    private fun initListeners() {
        binding.btn1.setOnClickListener {
            uncheckAllExcept(binding.btn1)
        }
        binding.btn2.setOnClickListener {
            uncheckAllExcept(binding.btn2)
        }
        binding.btn3.setOnClickListener {
            uncheckAllExcept(binding.btn3)
        }
        binding.btn4.setOnClickListener {
            uncheckAllExcept(binding.btn4)
        }
        binding.btn5.setOnClickListener {
            uncheckAllExcept(binding.btn5)
        }


        binding.btnNext.setOnClickListener {
            categoryViewModel.fetchCategory(
                Categories(
                    binding.txtID.toString(),
                    binding.title.toString(),
                    binding.imgurl.toString(),
                    binding.status.toString()


                )
            )
            addPostsFragment()

        }

    }

    private fun addPostsFragment() {
         val addPostsFragment = AddPostsFragment()
         parentFragmentManager.beginTransaction()
             .add(R.id.mainContainer, addPostsFragment, null)
             .addToBackStack(null)
             .commit()


    }

    private fun uncheckAllExcept(clickedButton: RadioButton) {
        val buttons = listOf(binding.btn1, binding.btn2, binding.btn3, binding.btn4, binding.btn5)
        buttons.forEach { button ->
            if (button != clickedButton) {
                button.isChecked = false
            }
        }
    }

    private fun initModels() {
        categoryViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                CategoryRepository(
                    CategoryApiService.getInstance()
                )
            )
        )[CategoryViewModel::class.java]
    }

    private fun initObserver() {
        categoryViewModel.categoryMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
               //finish()
                //startMainActivity()
            }
        }
    }

}
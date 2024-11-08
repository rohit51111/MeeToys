package com.bitcodetech.meetoys.categories.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.meetoys.auth.register.models.UserPostModel
import com.bitcodetech.meetoys.categories.models.Categories
import com.bitcodetech.meetoys.categories.repository.CategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(
    private val categoryRepository: CategoryRepository) : ViewModel() {

    val categoryMutableLiveData = MutableLiveData<Boolean>()

   // val categorys = ArrayList<Categories>()

    fun fetchCategory(
        categories: Categories
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val categoryss = categoryRepository.fetchCategory(categories)
            Log.e("tag",categoryss.toString())

            withContext(Dispatchers.Main) {
                //this@CategoryViewModel.categorys.addAll(listOf(categoryss))
                categoryMutableLiveData.postValue(true)
            }
        }
    }
}
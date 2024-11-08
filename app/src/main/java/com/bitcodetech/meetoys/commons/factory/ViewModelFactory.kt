package com.bitcodetech.meetoys.commons.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.meetoys.EditProfile.Model.EditProfileModel
import com.bitcodetech.meetoys.EditProfile.Repositary.EditRepository
import com.bitcodetech.meetoys.EditProfile.ViewModel.EditViewModel
import com.bitcodetech.meetoys.addposts.repository.AddPostRepository
import com.bitcodetech.meetoys.addposts.viewmodels.AddPostViewModel
import com.bitcodetech.meetoys.auth.login.repository.LoginRepository
import com.bitcodetech.meetoys.auth.login.viewmodel.LoginViewModel
import com.bitcodetech.meetoys.ownerdetails.repository.OwnerDetailsRepository
import com.bitcodetech.meetoys.ownerdetails.viewmodel.OwnerDetailsViewModel
import com.bitcodetech.meetoys.posts.repository.PostsRepository
import com.bitcodetech.meetoys.posts.viewmodel.PostsViewModel
import com.bitcodetech.meetoys.auth.register.repository.RegistrationRepository
import com.bitcodetech.meetoys.auth.register.viewmodel.RegistrationViewModel
import com.bitcodetech.meetoys.categories.models.Categories
import com.bitcodetech.meetoys.categories.repository.CategoryRepository
import com.bitcodetech.meetoys.categories.viewModel.CategoryViewModel
import com.bitcodetech.meetoys.commons.repository.Repository
import com.bitcodetech.meetoys.myposts.repository.MyPostRepository
import com.bitcodetech.meetoys.myposts.viewmodel.MyPostViewModel
import com.bitcodetech.meetoys.myprofile.repository.MyProfileRepository
import com.bitcodetech.meetoys.myprofile.viewmodel.MyProfileViewModel
import com.bitcodetech.meetoys.ourposts.repository.OurPostRepositary
import com.bitcodetech.meetoys.ourposts.viewmodel.OurPostViewModel
import java.lang.Exception

class ViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(LoginViewModel::class.java) && repository is LoginRepository) {
            return LoginViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(PostsViewModel::class.java) && repository is PostsRepository) {
            return PostsViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(AddPostViewModel::class.java) && repository is AddPostRepository) {
            return AddPostViewModel(repository) as T
        }
        /*if(modelClass.isAssignableFrom(PostDetailsViewModel::class.java) && repository is PostDetailsRepository) {
            return PostDetailsViewModel(repository) as T
        }*/
        if(modelClass.isAssignableFrom(OwnerDetailsViewModel::class.java) && repository is OwnerDetailsRepository) {
            return OwnerDetailsViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(RegistrationViewModel::class.java) && repository is RegistrationRepository) {
            return RegistrationViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(MyPostViewModel::class.java) && repository is MyPostRepository) {
            return MyPostViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(CategoryViewModel::class.java) && repository is CategoryRepository) {
            return CategoryViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(MyProfileViewModel::class.java) && repository is MyProfileRepository) {
            return MyProfileViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(EditViewModel::class.java) && repository is EditRepository) {
            return EditViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(OurPostViewModel::class.java) && repository is OurPostRepositary) {
            return OurPostViewModel(repository) as T
        }



        throw Exception("Unable to create view model...")
    }




}
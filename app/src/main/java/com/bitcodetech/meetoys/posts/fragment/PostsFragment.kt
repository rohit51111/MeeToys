package com.bitcodetech.meetoys.posts.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.meetoys.ourposts.fragment.OurDetailFragment
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.addposts.fragments.AddPostsFragment
import com.bitcodetech.meetoys.myposts.fragments.MyPostFragment
import com.bitcodetech.meetoys.posts.models.Post
import com.bitcodetech.meetoys.posts.repository.PostsRepository
import com.bitcodetech.meetoys.posts.viewmodel.PostsViewModel
import com.bitcodetech.meetoys.databinding.PostsFragmentBinding
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.myprofile.fragments.MyProfileFragment
import com.bitcodetech.meetoys.ourposts.fragment.OurPostFragment
import com.bitcodetech.meetoys.posts.adapter.PostsAdapter
import com.bitcodetech.meetoys.posts.network.PostsApiService

class PostsFragment : Fragment() {

    private lateinit var binding: PostsFragmentBinding
    private lateinit var postsViewModel: PostsViewModel
    private lateinit var postsAdapter: PostsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostsFragmentBinding.inflate(layoutInflater)


        initViews()
        initViewModels()
        initAdapter()
        initObserver()
        initListeners()
        bottomNavigation()

        postsViewModel.fetchPosts()

        return binding.root

    }


    private fun initListeners() {
        binding.recyclerPosts.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        postsViewModel.fetchPosts()
                    }
                }
            })
        /*binding.btnAddPosts.setOnClickListener {
           //addPostsFragment()
            categories()

        }*/
        postsAdapter.onPostClickListener =
            object : PostsAdapter.OnPostClickListener {
                override fun onPostListener(
                    post: Post,
                    position: Int,
                    postsAdapter: PostsAdapter
                ) {
                    showDetailsFragment(post)
                    // Log.e("tag","event deligationn model work")
                }
            }
    }

    private fun showDetailsFragment(post: Post) {
        val detailsFragment = DetailsFragment()

        //val input = Bundle()
        val bundle = Bundle()
        bundle.putSerializable("posts",post)
        detailsFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, detailsFragment, null)
            .addToBackStack(null)
            .commit()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        postsViewModel.postsMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                postsAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initAdapter() {
        postsAdapter = PostsAdapter(postsViewModel.posts)
        binding.recyclerPosts.adapter = postsAdapter
    }
    private fun initViewModels() {
        postsViewModel = ViewModelProvider(
            this,
            ViewModelFactory(PostsRepository(PostsApiService.getInstance())
            )
        )[PostsViewModel::class.java]
    }

    private fun initViews() {
        binding.recyclerPosts.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun bottomNavigation(){
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.aboutUs -> currentFragment(PostsFragment())

                R.id.myPost -> currentFragment(OurPostFragment())

                R.id.profile -> currentFragment(MyProfileFragment())

                R.id.addpost -> currentFragment(AddPostsFragment())

               /* R.id.fav -> currentFragment(OurPostFragment())*/

            }
            true
        }
    }
    private fun currentFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }
}
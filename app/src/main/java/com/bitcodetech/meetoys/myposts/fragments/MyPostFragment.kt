package com.bitcodetech.meetoys.myposts.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.MyPostFragmentBinding
import com.bitcodetech.meetoys.myposts.adapter.MyPostAdapter
import com.bitcodetech.meetoys.myposts.model.MyPost
import com.bitcodetech.meetoys.myposts.network.MyPostsApiService
import com.bitcodetech.meetoys.myposts.repository.MyPostRepository
import com.bitcodetech.meetoys.myposts.viewmodel.MyPostViewModel


class MyPostFragment : Fragment() {

    private lateinit var binding : MyPostFragmentBinding
    private lateinit var myPostViewModel : MyPostViewModel
    private lateinit var myPostAdapter : MyPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyPostFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initAdapter()
        initObserver()
        initListeners()

        myPostViewModel.fetchPosts()
        setHasOptionsMenu(true)
        binding.root.setOnClickListener {  }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun initListeners() {
        binding.recyclerMyPost.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        myPostViewModel.fetchPosts()
                    }
                }
            })

        myPostAdapter.onMyPostClickListener =
            object : MyPostAdapter.OnMyPostClickListener {
                override fun onMyPostListener(
                    myPost: MyPost,
                    position: Int,
                    myPostAdapter: MyPostAdapter
                ) {
                    showDetailsFragment(myPost)
                    // Log.e("tag","event deligationn model work")
                }
            }

    }


    private fun showDetailsFragment(myPost: MyPost) {
        val showDetailsFragment = ShowDetailsFragment()

        val bundles = Bundle()
        bundles.putSerializable("myposts",myPost)
        showDetailsFragment.arguments = bundles

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, showDetailsFragment, null)
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        myPostViewModel.myPostsMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                myPostAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun initAdapter() {
        myPostAdapter = MyPostAdapter(myPostViewModel.myPosts)
        binding.recyclerMyPost.adapter = myPostAdapter
    }

    private fun initViewModels() {
        myPostViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                MyPostRepository(MyPostsApiService.getInstance())
            )
        )[MyPostViewModel::class.java]
    }

    private fun initViews() {
        binding.recyclerMyPost.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}

// change My Post section & only api service
// change in showdetail.xml also
// after 1 day implement delete option after kajal code ready


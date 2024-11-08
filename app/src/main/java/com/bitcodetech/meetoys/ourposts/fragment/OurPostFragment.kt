package com.bitcodetech.meetoys.ourposts.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.meetoys.ourposts.adapter.OurPostAdaptar
import com.bitcodetech.meetoys.ourposts.models.OurPost
import com.bitcodetech.meetoys.ourposts.network.OurPostApiService
import com.bitcodetech.meetoys.ourposts.repository.OurPostRepositary
import com.bitcodetech.meetoys.ourposts.viewmodel.OurPostViewModel
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.commons.factory.ViewModelFactory
import com.bitcodetech.meetoys.databinding.FragmentOurPostBinding
import com.bitcodetech.meetoys.posts.adapter.PostsAdapter
import com.bitcodetech.meetoys.posts.models.Post


class OurPostFragment : Fragment() {


    private lateinit var binding: FragmentOurPostBinding
    private lateinit var ourPostViewModel: OurPostViewModel
    private lateinit var ourPostAdaptar: OurPostAdaptar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOurPostBinding.inflate(layoutInflater)



        initViews()
        initViewModels()
        initAdapter()
        initObserver()
        initListeners()

        ourPostViewModel.ourfetchPosts()
        return binding.root

    }





    private fun initListeners() {
       /* binding.recyclerMyPost.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        ourPostViewModel.ourfetchPosts()
                    }
                }
            })*/



        ourPostAdaptar.onOurPostClickListner =
            object : OurPostAdaptar.OnOurPostClickListner{
                override fun onOurPostListner(
                    ourPost: OurPost,
                    position: Int,
                    ourPostAdaptar: OurPostAdaptar
                ) {

                    OurDetailFragment(ourPost)

                }
            }

    }





    private fun OurDetailFragment(ourPost: OurPost){

        val ourDetailFragment = OurDetailFragment()
        val bundles = Bundle()
        bundles.putSerializable("posts",ourPost)
        ourDetailFragment.arguments = bundles

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, ourDetailFragment, null)
            .addToBackStack(null)
            .commit()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        ourPostViewModel.ourpostsMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                ourPostAdaptar.notifyDataSetChanged()
            }
        }
    }

    private fun initViewModels() {
        ourPostViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                OurPostRepositary(
                    OurPostApiService.getInstance()
                )
            )
        )[OurPostViewModel::class.java]
    }

    private fun initAdapter() {
        ourPostAdaptar = OurPostAdaptar(ourPostViewModel.ourposts)
        binding.recyclerMyPost.adapter = ourPostAdaptar
    }




    private fun initViews() {
        binding.recyclerMyPost.layoutManager = GridLayoutManager(requireContext(), 2)
    }




   /* private fun currentFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }*/

}
package com.bitcodetech.meetoys.posts.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.ownerdetails.fragments.OwnerDetailsFragment
import com.bitcodetech.meetoys.databinding.DetailsFragmentBinding
import com.bitcodetech.meetoys.posts.models.Post

class DetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private var posts : Post? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DetailsFragmentBinding.inflate(layoutInflater)
        initShowDetails()
        initViews()
        initListener()

        return binding.root
    }
    private fun initListener(){
/*
        binding.btnContact.setOnClickListener {
           */
/* val ownerDetailsFragment = OwnerDetailsFragment()
            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,ownerDetailsFragment,null)
                .addToBackStack(null)
                .commit()*//*
        }
*/
    }
    private fun initShowDetails() {
        if (arguments != null) {
            posts = requireArguments().getSerializable("posts") as Post
            Log.e("tag--", posts.toString())
            binding.posts = posts
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContact.setOnClickListener {

            // Show BottomSheetDialog
          /*  val dialog = BottomSheetDialog(requireContext())
            val bottomSheetView = layoutInflater.inflate(R.layout.owner_details_fragment, null)
            val btnClose = bottomSheetView.findViewById<Button>(R.id.btnDone)

            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(bottomSheetView)
            dialog.show()*/

            val ownerDetailsFragment = OwnerDetailsFragment()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer,ownerDetailsFragment,null)
                .addToBackStack(null)
                .commit()


        }
    }
    private fun initViews(){
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        
    }
}
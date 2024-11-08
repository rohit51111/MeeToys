package com.bitcodetech.meetoys.ourposts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.meetoys.ourposts.models.OurPost
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.databinding.OurPostViewBinding




class OurPostAdaptar(private val ourPost: ArrayList<OurPost>):RecyclerView.Adapter<OurPostAdaptar.OurPostViewHolder>() {

    private lateinit var binding: OurPostViewBinding


    interface OnOurPostClickListner{
        fun onOurPostListner(ourPost: OurPost,position: Int,ourPostAdaptar: OurPostAdaptar)
    }
     var onOurPostClickListner : OnOurPostClickListner? = null

    inner class OurPostViewHolder(view: View):RecyclerView.ViewHolder(view){


        val binding : OurPostViewBinding

        init {

            binding = OurPostViewBinding.bind(view)
            binding.root.setOnClickListener{

                onOurPostClickListner?.onOurPostListner(
                    ourPost[adapterPosition],
                    adapterPosition,
                    this@OurPostAdaptar
                )

            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurPostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.our_post_view, null)
        return OurPostViewHolder(view)
    }

    override fun getItemCount() = ourPost.size

    override fun onBindViewHolder(holder: OurPostViewHolder, position: Int) {
       val ourPost = ourPost[position]

        holder.binding.price.text = ourPost.price
        holder.binding.title.text = ourPost.title
        holder.binding.description.text = ourPost.description


    }
}


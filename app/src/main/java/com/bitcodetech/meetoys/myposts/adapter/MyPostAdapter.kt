package com.bitcodetech.meetoys.myposts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.databinding.MyPostViewBinding
import com.bitcodetech.meetoys.myposts.model.MyPost

class MyPostAdapter(private val myPost: ArrayList<MyPost>) : RecyclerView.Adapter<MyPostAdapter.MyPostViewHolder>() {

    private lateinit var binding: MyPostViewBinding

    interface OnMyPostClickListener{
        fun onMyPostListener(editMyPostModel: MyPost, position: Int, myPostAdapter: MyPostAdapter)
    }
    var onMyPostClickListener : OnMyPostClickListener? = null

    inner class MyPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding  : MyPostViewBinding

        init {
            binding = MyPostViewBinding.bind(view)
            binding.root.setOnClickListener {
                onMyPostClickListener?.onMyPostListener(
                    myPost[adapterPosition],
                    adapterPosition,
                    this@MyPostAdapter
                )
            }
        }
    }

    override fun getItemCount() = myPost.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_post_view, null)
        return MyPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        val post = myPost[position]

        // holder.binding.imgPosts.setImageResource(post.imageUrl)
        holder.binding.price.text = post.price
        holder.binding.title.text = post.title
        holder.binding.description.text = post.description
//        holder.imagePosts.setImageResource(post.imageUrl.toString().toInt())
//        holder.txtName.text = post.name
//        holder.txtAddress.text = post.address
//        holder.txtRent.text = post.rent.toString()

    }
}
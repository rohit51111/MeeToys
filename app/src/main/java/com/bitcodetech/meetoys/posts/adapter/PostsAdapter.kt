package com.bitcodetech.meetoys.posts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.meetoys.R
import com.bitcodetech.meetoys.posts.models.Post
import com.bitcodetech.meetoys.databinding.PostsViewBinding

class PostsAdapter(private val posts: ArrayList<Post>)
    : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private lateinit var binding: PostsViewBinding
    /*private var filteredList: ArrayList<Post> = posts*/
    interface OnPostClickListener{
        fun onPostListener(post: Post, position: Int, postsAdapter: PostsAdapter)
    }
     var onPostClickListener : OnPostClickListener? = null


    inner class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding  : PostsViewBinding

        init {
            binding = PostsViewBinding.bind(view)
            binding.root.setOnClickListener {
                onPostClickListener?.onPostListener(
                    posts[adapterPosition],
                    adapterPosition,
                    this@PostsAdapter
                )
            }
        }
    }

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.posts_view, null)
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = posts[position]

        //holder.binding.imgPosts.setImageResource(post.images_url.toString().toInt())
        holder.binding.price.text = post.price
        holder.binding.title.text = post.title
        holder.binding.description.text = post.description
//        holder.imagePosts.setImageResource(post.imageUrl.toString().toInt())
//        holder.txtName.text = post.name
//        holder.txtAddress.text = post.address
//        holder.txtRent.text = post.rent.toString()
       /* holder.binding.imgPosts.setImageResource(post.imageUrl.toString().toInt())
        holder.binding.txtName.text = post.name
        holder.binding.txtAddress.text = post.address
        holder.binding.txtRent.text = post.rent.toString()*/

    }
}
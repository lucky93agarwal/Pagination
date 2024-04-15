package com.msl.mytasktoday.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.msl.mytasktoday.Model.Post
import com.msl.mytasktoday.R

class PostAdapter(
    private val onItemClick: (Post) -> Unit
) : PagingDataAdapter<Post, RecyclerView.ViewHolder>(POST_COMPARATOR) {

    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_POST) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
            PostViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_POST) {
            val post = getItem(position)
            post?.let {
                (holder as PostViewHolder).bind(it, onItemClick)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < itemCount && getItem(position) == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_POST
        }
    }

    fun showLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)


        fun bind(post: Post, onItemClick: (Post) -> Unit) {
            // Bind post data to views
            textViewTitle.text = post.title
            textViewId.text = post.id.toString()
            itemView.setOnClickListener { onItemClick(post) }
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val VIEW_TYPE_POST = 1
        private const val VIEW_TYPE_LOADING = 2

        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}
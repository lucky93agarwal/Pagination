package com.msl.mytasktoday.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msl.mytasktoday.Model.Post
import com.msl.mytasktoday.R

class PostListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Post, PostListAdapter.PostViewHolder>(DIFF_CALLBACK) {

    interface OnItemClickListener {
        fun onItemClick(post: Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = getItem(position)
        holder.textViewId.text = currentPost.id.toString()
        holder.textViewTitle.text = currentPost.title
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val post = getItem(position)
                listener.onItemClick(post)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }
}
package com.msl.mytasktoday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msl.mytasktoday.Adapter.PostAdapter
import com.msl.mytasktoday.Viewmodel.PostViewModel
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val viewModel: PostViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        setupRecyclerView()




        lifecycleScope.launch {
            viewModel.posts.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }
    private fun setupRecyclerView() {
        adapter = PostAdapter { _ ->
            // Handle item click here
            // For example, navigate to detail activity
            /*val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("postId", post.id)
                startActivity(intent)*/
        }
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}
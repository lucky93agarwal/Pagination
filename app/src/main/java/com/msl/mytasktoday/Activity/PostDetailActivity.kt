package com.msl.mytasktoday.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.msl.mytasktoday.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostDetailActivity : AppCompatActivity() {
    private lateinit var textViewDetails: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        textViewDetails = findViewById(R.id.textViewDetails)

        val postId = intent.getIntExtra("postId", -1)

        GlobalScope.launch(Dispatchers.Main) {
            val details = fetchPostDetails(postId)
            textViewDetails.text = details
        }
    }
    private suspend fun fetchPostDetails(postId: Int): String {
        // Simulating heavy computation
        return "Details for post $postId"
    }
}
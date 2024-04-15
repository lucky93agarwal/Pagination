package com.msl.mytasktoday.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.msl.mytasktoday.Model.Post
import com.msl.mytasktoday.PagingSource.PostPagingSource
import kotlinx.coroutines.flow.Flow

class PostViewModel : ViewModel() {
    val posts: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { PostPagingSource() }
    ).flow
}
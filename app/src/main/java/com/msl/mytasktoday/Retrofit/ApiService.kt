package com.msl.mytasktoday.Retrofit

import com.msl.mytasktoday.Model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Post>
}
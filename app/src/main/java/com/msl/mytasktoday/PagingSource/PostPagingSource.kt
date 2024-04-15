package com.msl.mytasktoday.PagingSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msl.mytasktoday.Model.Post
import com.msl.mytasktoday.Retrofit.ApiClient
import retrofit2.HttpException
import java.io.IOException
class PostPagingSource : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = ApiClient.apiService.getPosts(nextPageNumber, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        // Start pagination from the first page (index 1) regardless of the previous state
        return 1
    }
}
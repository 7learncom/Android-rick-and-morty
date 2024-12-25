package com.mhd.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mhd.rickandmorty.data.network.ApiService
import com.mhd.rickandmorty.data.network.response.EpisodePagingResponse
import javax.inject.Inject

class EpisodesPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, EpisodePagingResponse.Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodePagingResponse.Episode> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getEpisodes(nextPageNumber)
            LoadResult.Page(
                data = response.episodes,
                prevKey = null,
                nextKey = if (response.pagingInfo.next != null) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EpisodePagingResponse.Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)
        }?.let { anchorPage ->
            anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
        }
    }
}
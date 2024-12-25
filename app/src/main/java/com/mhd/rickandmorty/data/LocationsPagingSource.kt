package com.mhd.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mhd.rickandmorty.data.network.ApiService
import com.mhd.rickandmorty.data.network.response.LocationsPagingResponse
import javax.inject.Inject

class LocationsPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, LocationsPagingResponse.Location>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationsPagingResponse.Location> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getLocations(nextPageNumber)
            LoadResult.Page(
                data = response.locations,
                prevKey = null,
                nextKey = if (response.pagingInfo.next != null) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LocationsPagingResponse.Location>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)
        }?.let { anchorPage ->
            anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
        }
    }
}
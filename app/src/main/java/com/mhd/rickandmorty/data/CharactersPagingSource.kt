package com.mhd.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mhd.rickandmorty.data.network.ApiService
import com.mhd.rickandmorty.data.network.response.CharactersPagingResponse
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, CharactersPagingResponse.Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersPagingResponse.Character> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getCharacters(nextPageNumber)
            LoadResult.Page(
                data = response.characters,
                prevKey = null,
                nextKey = if (response.pagingInfo.next != null) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharactersPagingResponse.Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)
        }?.let { anchorPage ->
            anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
        }
    }
}
package com.mhd.rickandmorty.features.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mhd.rickandmorty.data.EpisodesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesPagingSource: EpisodesPagingSource
) : ViewModel() {

    val episodes = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { episodesPagingSource }
    ).flow.cachedIn(viewModelScope)
}
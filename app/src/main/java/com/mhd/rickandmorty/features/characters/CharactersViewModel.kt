package com.mhd.rickandmorty.features.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mhd.rickandmorty.data.CharactersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersPagingSource: CharactersPagingSource,
) : ViewModel() {

    val characters = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { charactersPagingSource }
    ).flow.cachedIn(viewModelScope)

}
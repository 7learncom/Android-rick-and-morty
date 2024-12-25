package com.mhd.rickandmorty.features.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mhd.rickandmorty.data.LocationsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationsPagingSource: LocationsPagingSource
) : ViewModel() {

    val locations = Pager(
        config = PagingConfig(pageSize = 20),
    ) {
        locationsPagingSource
    }.flow.cachedIn(viewModelScope)

}
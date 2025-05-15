package com.mhd.rickandmorty.data.network

import com.mhd.rickandmorty.data.network.response.CharactersPagingResponse
import com.mhd.rickandmorty.data.network.response.EpisodesPagingResponse
import com.mhd.rickandmorty.data.network.response.LocationsPagingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharactersPagingResponse

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): LocationsPagingResponse

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): EpisodesPagingResponse
}
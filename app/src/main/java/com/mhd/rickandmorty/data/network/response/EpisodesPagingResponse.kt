package com.mhd.rickandmorty.data.network.response


import com.google.gson.annotations.SerializedName

data class EpisodesPagingResponse(
    @SerializedName("info")
    val pagingInfo: PagingInfo,
    @SerializedName("results")
    val episodes: List<Episode>
) {

    data class Episode(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("characters")
        val characters: List<String>,
        @SerializedName("created")
        val created: String,
        @SerializedName("episode")
        val episode: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}
package com.mhd.rickandmorty.data.network.response


import com.google.gson.annotations.SerializedName

data class LocationsPagingResponse(
    @SerializedName("info")
    val pagingInfo: PagingInfo,
    @SerializedName("results")
    val locations: List<Location>
) {
    data class Location(
        @SerializedName("created")
        val created: String,
        @SerializedName("dimension")
        val dimension: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("residents")
        val residents: List<String>,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String
    )
}
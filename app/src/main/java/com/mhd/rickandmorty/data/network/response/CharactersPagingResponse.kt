package com.mhd.rickandmorty.data.network.response


import com.google.gson.annotations.SerializedName

data class CharactersPagingResponse(
    @SerializedName("info")
    val pagingInfo: PagingInfo,
    @SerializedName("results")
    val characters: List<Character>
) {

    data class Character(
        @SerializedName("created")
        val created: String,
        @SerializedName("episode")
        val episode: List<String>,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("location")
        val location: Location,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin")
        val origin: Origin,
        @SerializedName("species")
        val species: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String
    ) {
        data class Location(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )

        data class Origin(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )
    }
}
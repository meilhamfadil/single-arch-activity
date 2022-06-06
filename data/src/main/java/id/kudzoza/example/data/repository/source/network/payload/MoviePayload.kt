package id.kudzoza.example.data.repository.source.network.payload


import com.google.gson.annotations.SerializedName

data class MoviePayload(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int,
)
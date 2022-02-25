package id.kudzoza.example.domain.payload


import com.google.gson.annotations.SerializedName

data class MoviesPayload(
    @SerializedName("poster")
    val poster: String,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int
)
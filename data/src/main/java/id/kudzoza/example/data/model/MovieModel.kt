package id.kudzoza.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: Int,
    val poster: String,
    val synopsis: String,
    val title: String,
    val year: Int,
) : Parcelable
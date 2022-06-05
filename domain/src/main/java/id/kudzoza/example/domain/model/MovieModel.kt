package id.kudzoza.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val poster: String,
    val synopsis: String,
    val title: String,
    val year: Int,
) : Parcelable
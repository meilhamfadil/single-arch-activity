package id.kudzoza.example.domain.model

import id.kudzoza.example.domain.payload.MoviesPayload

data class MovieModel(
    val poster: String,
    val synopsis: String,
    val title: String,
    val year: Int,
) {

    companion object {
        fun fromPayload(payload: MoviesPayload) = MovieModel(
            poster = payload.poster,
            synopsis = payload.synopsis,
            title = payload.title,
            year = payload.year,
        )
    }

}
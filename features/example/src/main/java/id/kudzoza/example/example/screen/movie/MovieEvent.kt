package id.kudzoza.example.example.screen.movie

import id.kudzoza.example.domain.model.MovieModel

sealed class MovieEvent {
    object MoviesRequest : MovieEvent()
    object MovieRefresh : MovieEvent()
    data class MovieClicked(val movie: MovieModel) : MovieEvent()
}
package id.kudzoza.example.example.screen.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kudzoza.core.base.BaseViewModel
import id.kudzoza.core.base.SingleLiveEvent
import id.kudzoza.core.data.model.DataState
import id.kudzoza.core.util.launch
import id.kudzoza.core.util.main
import id.kudzoza.example.domain.model.MovieModel
import id.kudzoza.example.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@HiltViewModel
class MovieVM @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : BaseViewModel() {

    private val _movieList: MutableLiveData<DataState<List<MovieModel>>> = MutableLiveData()
    val movieList: LiveData<DataState<List<MovieModel>>> get() = _movieList
    val eventMovieClicked: SingleLiveEvent<MovieModel> = SingleLiveEvent()

    fun callEvent(event: MovieEvent) {
        when (event) {
            is MovieEvent.MoviesRequest -> getMovies()
            is MovieEvent.MovieRefresh -> getMovies()
            is MovieEvent.MovieClicked -> eventMovieClicked.value = event.movie
        }
    }

    private fun getMovies() = launch {
        movieUseCase.getMovies().onEach {
            main { _movieList.value = it }
        }.launchIn(viewModelScope)
    }

}
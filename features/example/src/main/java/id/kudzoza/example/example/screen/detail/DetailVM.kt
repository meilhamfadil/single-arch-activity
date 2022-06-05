package id.kudzoza.example.example.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kudzoza.core.base.BaseViewModel
import id.kudzoza.core.util.launch
import id.kudzoza.core.util.toObject
import id.kudzoza.example.domain.model.MovieModel
import id.kudzoza.example.domain.usecase.MovieUseCase
import javax.inject.Inject

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@HiltViewModel
class DetailVM @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val movieUseCase: MovieUseCase,
) : BaseViewModel() {

    private val _movie: MutableLiveData<MovieModel?> = MutableLiveData()
    val movie: LiveData<MovieModel?> get() = _movie

    init {
        showDetail()
    }

    private fun showDetail() {
        _movie.value = toObject(
            savedStateHandle.get("MOVIE"),
            MovieModel::class.java
        )
        launch {
            movieUseCase.getMovies()
        }
    }

}

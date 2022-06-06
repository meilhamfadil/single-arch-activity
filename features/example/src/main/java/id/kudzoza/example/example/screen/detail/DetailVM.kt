package id.kudzoza.example.example.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kudzoza.core.base.BaseViewModel
import id.kudzoza.core.data.model.DataState
import id.kudzoza.core.util.launch
import id.kudzoza.example.data.domain.MovieUseCase
import id.kudzoza.example.data.model.MovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private val _movie: MutableLiveData<DataState<MovieModel>> = MutableLiveData()
    val movie: LiveData<DataState<MovieModel>> get() = _movie

    init {
        showDetail()
    }

    private fun showDetail() {
        val id: Int? = savedStateHandle.get("movie")
        if (id == null)
            eventShowDefaultNotFound.call()
        else
            launch {
                val movie = movieUseCase.getMovie(id)
                movie.onEach {
                    _movie.value = it
                }.launchIn(viewModelScope)
            }
    }

}

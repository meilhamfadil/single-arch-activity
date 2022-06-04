package id.kudzoza.example.example.screen.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kudzoza.core.base.BaseViewModel
import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.util.launch
import id.kudzoza.core.util.main
import id.kudzoza.example.domain.model.MovieModel
import id.kudzoza.example.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@HiltViewModel
class MovieVM @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : BaseViewModel() {

    private val _movieList: MutableLiveData<Resource<List<MovieModel>>> = MutableLiveData()
    val movieList: LiveData<Resource<List<MovieModel>>> get() = _movieList

    init {
        getMovies()
    }

    fun getMovies() = launch {
        movieUseCase.getMovies().collect {
            main { _movieList.value = it }
        }
    }

}
package id.kudzoza.example.example.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.kudzoza.core.util.toObject
import id.kudzoza.example.domain.model.MovieModel
import javax.inject.Inject

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@HiltViewModel
class DetailVM @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

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
    }

}

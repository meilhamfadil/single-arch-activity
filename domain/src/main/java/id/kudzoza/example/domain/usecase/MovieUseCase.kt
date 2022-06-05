package id.kudzoza.example.domain.usecase

import id.kudzoza.core.data.model.DataState
import id.kudzoza.core.util.errorState
import id.kudzoza.core.util.finishState
import id.kudzoza.core.util.loadingState
import id.kudzoza.core.util.successState
import id.kudzoza.example.domain.model.MovieModel
import id.kudzoza.example.domain.payload.MovieMapper
import id.kudzoza.example.domain.preference.GlobalPreference
import id.kudzoza.example.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

class MovieUseCase(
    private val movieRepository: MovieRepository,
    private val globalPreference: GlobalPreference,
    private val movieMapper: MovieMapper,
) {

    suspend fun getMovies(): Flow<DataState<List<MovieModel>>> {
        return flow {
            emit(loadingState())
            delay(1000)
            globalPreference.storeAccessToken(System.currentTimeMillis().toString())
            movieRepository.getMovies().let {
                val data = movieMapper.mapFromEntityList(it.data.orEmpty())
                if (it.ok)
                    emit(successState(data))
                else
                    emit(errorState(it.message))
            }
            emit(finishState())
        }
    }

}
package id.kudzoza.example.data.domain

import id.kudzoza.core.data.model.DataSource
import id.kudzoza.core.data.model.DataState
import id.kudzoza.core.util.errorState
import id.kudzoza.core.util.finishState
import id.kudzoza.core.util.loadingState
import id.kudzoza.core.util.successState
import id.kudzoza.example.data.model.MovieModel
import id.kudzoza.example.data.repository.MovieRepository
import id.kudzoza.example.data.repository.source.cache.dao.MovieDao
import id.kudzoza.example.data.repository.source.cache.entity.MovieEntityMapper
import id.kudzoza.example.data.repository.source.network.payload.MoviePayloadMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

class MovieUseCase(
    private val movieRepository: MovieRepository,
    private val movieDao: MovieDao,
    private val moviePayloadMapper: MoviePayloadMapper,
    private val movieEntityMapper: MovieEntityMapper,
) {

    suspend fun getMovies(): Flow<DataState<List<MovieModel>>> {
        return flow {
            emit(loadingState())

            delay(1000)
            val payload = movieRepository.getMovies(DataSource.NETWORK)

            if (payload.ok) {
                for (movie in payload.data.orEmpty())
                    movieDao.insertMovie(movieEntityMapper.mapToEntity(movie))
            } else {
                emit(errorState(payload.message))
            }

            val movies = movieRepository.getMovies(DataSource.LOCAL)
            if (movies.ok) {
                emit(successState(movies.data))
            } else {
                emit(errorState(movies.message))
            }

            emit(finishState())
        }
    }

    suspend fun getMovie(id: Int): Flow<DataState<MovieModel>> {
        return flow {
            emit(loadingState())

            delay(1000)
            val movie = movieRepository.getMovie(id)
            if (movie.ok)
                emit(successState(movie.data))
            else
                emit(errorState(movie.message))

            emit(finishState())
        }
    }

}
package id.kudzoza.example.data.repository

import id.kudzoza.core.data.model.DataSource
import id.kudzoza.core.data.model.Payload
import id.kudzoza.core.util.failure
import id.kudzoza.core.util.success
import id.kudzoza.example.data.model.MovieModel
import id.kudzoza.example.data.repository.source.cache.dao.MovieDao
import id.kudzoza.example.data.repository.source.cache.entity.MovieEntityMapper
import id.kudzoza.example.data.repository.source.network.MovieService
import id.kudzoza.example.data.repository.source.network.payload.MoviePayloadMapper
import javax.inject.Inject

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val movieEntityMapper: MovieEntityMapper,
    private val moviePayloadMapper: MoviePayloadMapper,
) : MovieRepository {

    override suspend fun getMovies(source: DataSource): Payload<List<MovieModel>> {
        return try {
            success(
                when (source) {
                    DataSource.LOCAL ->
                        movieEntityMapper.mapFromListEntity(movieDao.getAllMovie())
                    DataSource.NETWORK ->
                        moviePayloadMapper.mapFromListEntity(movieService.getMovies())
                    DataSource.MOCK ->
                        emptyList()
                }
            )
        } catch (e: Exception) {
            failure(e)
        }
    }

}
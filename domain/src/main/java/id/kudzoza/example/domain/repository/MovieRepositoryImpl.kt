package id.kudzoza.example.domain.repository

import id.kudzoza.core.data.model.Payload
import id.kudzoza.core.util.failure
import id.kudzoza.core.util.success
import id.kudzoza.example.domain.payload.MoviesPayload
import id.kudzoza.example.domain.service.MovieService
import javax.inject.Inject

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
) : MovieRepository {

    override suspend fun getMovies(): Payload<List<MoviesPayload>> {
        return try {
            success(movieService.getMovies())
        } catch (e: Exception) {
            failure(e)
        }
    }

}
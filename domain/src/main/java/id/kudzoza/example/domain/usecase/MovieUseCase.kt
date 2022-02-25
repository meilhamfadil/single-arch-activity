package id.kudzoza.example.domain.usecase

import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.util.rError
import id.kudzoza.core.util.rFinish
import id.kudzoza.core.util.rLoading
import id.kudzoza.core.util.rSuccess
import id.kudzoza.example.domain.preference.GlobalPreference
import id.kudzoza.example.domain.model.MovieModel
import id.kudzoza.example.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val globalPreference: GlobalPreference,
) {

    suspend fun getMovies(): Flow<Resource<List<MovieModel>>> {
        return flow {
            emit(rLoading())
            delay(1000)
            globalPreference.storeAccessToken(System.currentTimeMillis().toString())
            movieRepository.getMovies().let {
                if (it.ok) emit(rSuccess(it.data?.map { m -> MovieModel.fromPayload(m) }))
                else emit(rError(it.message))
            }
            emit(rFinish())
        }
    }

}
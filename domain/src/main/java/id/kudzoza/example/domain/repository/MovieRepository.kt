package id.kudzoza.example.domain.repository

import id.kudzoza.core.data.model.Payload
import id.kudzoza.example.domain.payload.MoviesPayload

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

interface MovieRepository {

    suspend fun getMovies(): Payload<List<MoviesPayload>>

}
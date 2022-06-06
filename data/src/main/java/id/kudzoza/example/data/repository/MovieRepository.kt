package id.kudzoza.example.data.repository

import id.kudzoza.core.data.model.DataSource
import id.kudzoza.core.data.model.Payload
import id.kudzoza.example.data.model.MovieModel

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

interface MovieRepository {

    suspend fun getMovies(
        source: DataSource,
    ): Payload<List<MovieModel>>

}
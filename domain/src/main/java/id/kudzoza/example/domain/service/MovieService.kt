package id.kudzoza.example.domain.service

import id.kudzoza.example.domain.payload.MoviesPayload
import retrofit2.http.GET

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

interface MovieService {

    @GET("/movies.json")
    suspend fun getMovies(): List<MoviesPayload>

}
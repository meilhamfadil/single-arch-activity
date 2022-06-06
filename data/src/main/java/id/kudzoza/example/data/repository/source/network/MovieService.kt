package id.kudzoza.example.data.repository.source.network

import id.kudzoza.example.data.repository.source.network.payload.MoviePayload
import retrofit2.http.GET

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

interface MovieService {

    @GET("/movies.json")
    suspend fun getMovies(): List<MoviePayload>

}
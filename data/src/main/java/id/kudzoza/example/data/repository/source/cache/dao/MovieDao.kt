package id.kudzoza.example.data.repository.source.cache.dao

import androidx.room.*
import id.kudzoza.example.data.repository.source.cache.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAllMovie(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovie(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity): Long

    @Delete
    suspend fun removeMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

}
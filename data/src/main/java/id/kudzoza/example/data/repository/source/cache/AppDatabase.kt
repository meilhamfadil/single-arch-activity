package id.kudzoza.example.data.repository.source.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import id.kudzoza.example.data.repository.source.cache.dao.MovieDao
import id.kudzoza.example.data.repository.source.cache.entity.MovieEntity

@Database(entities = [
    MovieEntity::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
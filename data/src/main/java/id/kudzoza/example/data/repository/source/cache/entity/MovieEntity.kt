package id.kudzoza.example.data.repository.source.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "year")
    val year: Int,
)
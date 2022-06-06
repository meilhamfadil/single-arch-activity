package id.kudzoza.example.data.repository.source.cache.entity

import id.kudzoza.example.data.model.MovieModel
import id.kudzoza.example.data.util.EntityMapper
import javax.inject.Inject

class MovieEntityMapper
@Inject constructor() : EntityMapper<MovieEntity, MovieModel> {

    override fun mapFromEntity(entity: MovieEntity): MovieModel {
        return MovieModel(
            id = entity.id,
            poster = entity.poster,
            synopsis = entity.synopsis,
            title = entity.title,
            year = entity.year,
        )
    }

    override fun mapToEntity(domain: MovieModel): MovieEntity {
        return MovieEntity(
            id = domain.id,
            poster = domain.poster,
            synopsis = domain.synopsis,
            title = domain.title,
            year = domain.year,
        )
    }

    fun mapFromListEntity(entities: List<MovieEntity>): List<MovieModel> {
        return entities.map { mapFromEntity(it) }
    }

}
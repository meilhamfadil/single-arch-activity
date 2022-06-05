package id.kudzoza.example.domain.payload

import id.kudzoza.example.domain.model.MovieModel
import id.kudzoza.example.domain.util.EntityMapper
import javax.inject.Inject

class MovieMapper
@Inject constructor() : EntityMapper<MoviesPayload, MovieModel> {

    override fun mapFromEntity(entity: MoviesPayload): MovieModel {
        return MovieModel(
            poster = entity.poster,
            synopsis = entity.synopsis,
            title = entity.title,
            year = entity.year
        )
    }

    override fun mapToEntity(domain: MovieModel): MoviesPayload {
        return MoviesPayload(
            poster = domain.poster,
            synopsis = domain.synopsis,
            title = domain.title,
            year = domain.year
        )
    }

    fun mapFromEntityList(entities: List<MoviesPayload>): List<MovieModel> {
        return entities.map { mapFromEntity(it) }
    }

}
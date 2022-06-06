package id.kudzoza.example.data.repository.source.network.payload

import id.kudzoza.example.data.model.MovieModel
import id.kudzoza.example.data.util.EntityMapper
import javax.inject.Inject

class MoviePayloadMapper
@Inject constructor() : EntityMapper<MoviePayload, MovieModel> {

    override fun mapFromEntity(entity: MoviePayload): MovieModel {
        return MovieModel(
            id = entity.id,
            poster = entity.poster,
            synopsis = entity.synopsis,
            title = entity.title,
            year = entity.year
        )
    }

    override fun mapToEntity(domain: MovieModel): MoviePayload {
        return MoviePayload(
            id = domain.id,
            poster = domain.poster,
            synopsis = domain.synopsis,
            title = domain.title,
            year = domain.year
        )
    }

    fun mapFromListEntity(entities: List<MoviePayload>): List<MovieModel> {
        return entities.map { mapFromEntity(it) }
    }

}
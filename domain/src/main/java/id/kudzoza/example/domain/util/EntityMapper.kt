package id.kudzoza.example.domain.util

interface EntityMapper<ENTITY, DOMAIN> {

    fun mapFromEntity(entity: ENTITY): DOMAIN

    fun mapToEntity(domain: DOMAIN): ENTITY

}
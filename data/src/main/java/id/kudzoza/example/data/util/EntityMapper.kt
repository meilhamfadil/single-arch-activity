package id.kudzoza.example.data.util

interface EntityMapper<ENTITY, DOMAIN> {

    fun mapFromEntity(entity: ENTITY): DOMAIN

    fun mapToEntity(domain: DOMAIN): ENTITY

}
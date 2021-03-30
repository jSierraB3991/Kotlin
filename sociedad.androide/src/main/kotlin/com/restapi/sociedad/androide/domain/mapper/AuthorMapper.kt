package com.restapi.sociedad.androide.domain.mapper

interface AuthorMapper<DTO, DOMAIN> {

    fun getDto(domain: DOMAIN): DTO

    fun getDomain(dto: DTO): DOMAIN

    fun getListDto(domains: List<DOMAIN>): List<DTO>{
        return domains.map { domain -> getDto(domain) }
    }

    fun getListDomain(dtos: List<DTO>): List<DOMAIN> {
        return dtos.map { dto -> getDomain(dto) }
    }

}
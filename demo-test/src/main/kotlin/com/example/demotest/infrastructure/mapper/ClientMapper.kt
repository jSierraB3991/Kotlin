package com.example.demotest.infrastructure.mapper

import com.example.demotest.domain.model.Client
import com.example.demotest.infrastructure.dto.ClientDto
import com.example.demotest.infrastructure.request.ClientRequest
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.Mappings
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy
import org.springframework.data.domain.Page

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
)
abstract class ClientMapper {

    @Mappings
    abstract fun toDto(client: Client?): ClientDto
    @Mappings
    abstract fun toDtoFromRequest(clientRequest: ClientRequest?): ClientDto

    abstract fun toModel(clientRequest: ClientRequest?): Client

    @Mappings
    abstract fun toListToDto(list: Page<Client>): MutableList<ClientDto>
    @Mappings
    abstract fun toModelFromDto(cartDto: ClientDto?): Client

    abstract fun updateModel(cartDto: ClientDto?, @MappingTarget client: Client?)
}

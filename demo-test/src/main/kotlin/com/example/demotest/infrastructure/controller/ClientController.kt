package com.example.demotest.infrastructure.controller

import com.example.demotest.domain.model.Client
import com.example.demotest.infrastructure.dto.ClientDto
import com.example.demotest.infrastructure.mapper.ClientMapper
import com.example.demotest.infrastructure.request.ClientRequest
import com.example.demotest.infrastructure.request.OnCreate
import com.example.demotest.infrastructure.request.OnUpdate
import com.example.demotest.infrastructure.service.ClientService
import com.sipios.springsearch.anotation.SearchSpec
import io.swagger.annotations.Api
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController("client.v1")
@RequestMapping("/api/v1/client")
@Validated
@Api(tags = ["clients"])
class ClientController(
    private val clientService: ClientService,
    private val clientMapper: ClientMapper
) {
    @GetMapping
    fun index(pageable: Pageable, @SearchSpec spec: Specification<Client>?): Page<ClientDto> {
        return clientService.findAll(pageable, spec).map { client ->  clientMapper.toDto(client) }
    }

    @GetMapping("/{uuid}")
    @Cacheable("v1/client/show", key = "#uuid")
    fun show(@PathVariable uuid: UUID): ResponseEntity<ClientDto> {
        return ResponseEntity.ok().body(clientMapper.toDto(clientService.findByUuid(uuid)))
    }

    @PostMapping
    @CacheEvict("v1/client/index", beforeInvocation = true)
    fun create(
        @Validated(OnCreate::class) @RequestBody clientRequest: ClientRequest
    ): ResponseEntity<ClientDto> {
        return ResponseEntity.ok().body(clientMapper.toDto(clientService.save(clientRequest)))
    }

    @PatchMapping("/{uuid}")
    @Caching(
        evict = [
            CacheEvict("v1/client/show", key = "#uuid", allEntries = true, beforeInvocation = true),
            CacheEvict("v1/client/index", allEntries = true, beforeInvocation = true)
        ],
        put = [
            CachePut("v1/client/show", key = "#uuid")
        ]
    )
    fun update(
        @PathVariable uuid: UUID,
        @Validated(OnUpdate::class) @RequestBody clientRequest: ClientRequest
    ): ResponseEntity<ClientDto> {
        return ResponseEntity.ok().body(clientMapper.toDto(clientService.update(uuid, clientRequest)))
    }

}
package com.example.demotest.infrastructure.service

import com.example.demotest.domain.model.Client
import com.example.demotest.infrastructure.request.ClientRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.UUID
import java.util.Optional

interface ClientService {

    fun findAll(pageable: Pageable, spec: Specification<Client>?): Page<Client>

    fun findByUuid(uuid: UUID): Client

    fun save(clientRequest: ClientRequest): Client

    fun finByCode(code: Long): Client

    fun count(increment: Int): Long
}
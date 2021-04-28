package com.example.demotest.infrastructure.service.impl

import com.example.demotest.domain.model.Client
import com.example.demotest.infrastructure.mapper.ClientMapper
import com.example.demotest.infrastructure.repository.ClientRepository
import com.example.demotest.infrastructure.request.ClientRequest
import com.example.demotest.infrastructure.service.ClientService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID
import java.util.Optional

@Service("client.crud_service")
class ClientServiceImpl(
    private val clientCrudRepository: ClientRepository,
    private val clientMapper: ClientMapper
): ClientService {

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable, spec: Specification<Client>?): Page<Client> {
        return clientCrudRepository.findAll(Specification.where(spec), pageable)
    }

    @Transactional(readOnly = true)
    override fun findByUuid(uuid: UUID): Client {
        return clientCrudRepository.findById(uuid)
            .orElseThrow()
    }

    @Transactional
    override fun save(clientRequest: ClientRequest): Client {
        val client = clientMapper.toModel(clientRequest)
        return clientCrudRepository.save(client)
    }

    @Transactional
    override fun finByCode(code: Long): Client {
        return clientCrudRepository.findByCode(code)
            .orElseThrow()
    }

    override fun count(increment: Int): Long {
        return clientCrudRepository.count() + increment
    }
}
package com.example.demotest.infrastructure.repository

import com.example.demotest.domain.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository("client.crud_repository")
interface ClientRepository : JpaRepository<Client, UUID> , JpaSpecificationExecutor<Client>{

    fun findByCode(code: Long): Optional<Client>
}

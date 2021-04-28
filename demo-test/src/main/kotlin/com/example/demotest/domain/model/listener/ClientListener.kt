package com.example.demotest.domain.model.listener

import com.example.demotest.domain.model.Client
import com.example.demotest.infrastructure.service.ClientService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.PostLoad
import javax.persistence.PostPersist
import javax.persistence.PostUpdate
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@Component
class ClientListener {

    companion object {
        private lateinit var clientService: ClientService
    }
    @Autowired
    fun setProducer(clientService: ClientService) {
        Companion.clientService = clientService
    }

    private val log = LoggerFactory.getLogger(javaClass)

    @PostLoad
    fun postLoad(client: Client) {
        log.trace("postLoad -> client: $client")
    }

    @PrePersist
    fun prePersist(client: Client) {
        log.trace("prePersist -> client: $client")
        if (client.code == null) client.code = clientService.count(1)
    }

    @PostPersist
    fun postPersist(client: Client) {
        log.trace("postPersist -> client: $client")
    }

    @PreUpdate
    fun preUpdate(client: Client) {
        log.trace("preUpdate -> client: $client")
    }

    @PostUpdate
    fun postUpdate(client: Client) {
        log.trace("postUpdate -> client: $client")
    }
}

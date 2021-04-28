package com.example.demotest.domain.config

import com.example.demotest.domain.model.listener.EventListener
import org.hibernate.event.service.spi.EventListenerRegistry
import org.hibernate.event.spi.EventType
import org.hibernate.internal.SessionFactoryImpl
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Configuration
class HibernateListenerConfig(
    @PersistenceUnit
    private val emf: EntityManagerFactory,
    private val listener: EventListener
) {
    @PostConstruct
    protected fun init() {
        val registry = emf.unwrap(SessionFactoryImpl::class.java)
            .serviceRegistry.getService(EventListenerRegistry::class.java)
        registry.apply {
            appendListeners(EventType.PRE_INSERT, listener)
            appendListeners(EventType.PRE_LOAD, listener)
            appendListeners(EventType.PRE_UPDATE, listener)
            appendListeners(EventType.POST_INSERT, listener)
            appendListeners(EventType.POST_LOAD, listener)
            appendListeners(EventType.POST_UPDATE, listener)
        }
    }
}

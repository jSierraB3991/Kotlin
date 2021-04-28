package com.example.demotest.domain.model.listener

import org.hibernate.event.spi.PostInsertEvent
import org.hibernate.event.spi.PostInsertEventListener
import org.hibernate.event.spi.PostLoadEvent
import org.hibernate.event.spi.PostLoadEventListener
import org.hibernate.event.spi.PostUpdateEvent
import org.hibernate.event.spi.PostUpdateEventListener
import org.hibernate.event.spi.PreInsertEvent
import org.hibernate.event.spi.PreInsertEventListener
import org.hibernate.event.spi.PreLoadEvent
import org.hibernate.event.spi.PreLoadEventListener
import org.hibernate.event.spi.PreUpdateEvent
import org.hibernate.event.spi.PreUpdateEventListener
import org.hibernate.persister.entity.EntityPersister
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("listener.event_listener")
class EventListener() :
    PreInsertEventListener,
    PreLoadEventListener,
    PreUpdateEventListener,
    PostInsertEventListener,
    PostLoadEventListener,
    PostUpdateEventListener {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun onPreInsert(event: PreInsertEvent): Boolean {
        log.trace("onPreInsert -> event: $event")
        return false
    }
    override fun onPreLoad(event: PreLoadEvent) {
        log.trace("onPreLoad -> event: $event")
    }
    override fun onPreUpdate(event: PreUpdateEvent): Boolean {
        log.trace("onPreUpdate -> event: $event")
        return false
    }

    override fun requiresPostCommitHanding(persister: EntityPersister): Boolean {
        log.trace("requiresPostCommitHanding -> persister: $persister")
        return false
    }

    override fun onPostUpdate(event: PostUpdateEvent) {
        log.trace("onPostUpdate -> event: $event")
    }
    override fun onPostInsert(event: PostInsertEvent) {
        log.trace("onPostInsert -> event: $event")
    }
    override fun onPostLoad(event: PostLoadEvent) {
        log.trace("onPostLoad -> event: $event")
    }
}

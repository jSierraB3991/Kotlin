package com.kotlin.developer.demo.kafka.service.impl

import com.kotlin.developer.demo.kafka.model.User
import com.kotlin.developer.demo.kafka.service.KafkaListenerService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class KafkaListenerServiceImpl: KafkaListenerService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun listenerMessage(user: User) {
        log.info(user.toString())
    }
}
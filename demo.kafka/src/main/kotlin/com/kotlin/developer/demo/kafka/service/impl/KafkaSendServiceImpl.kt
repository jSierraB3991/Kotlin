package com.kotlin.developer.demo.kafka.service.impl

import com.kotlin.developer.demo.kafka.model.User
import com.kotlin.developer.demo.kafka.service.KafkaSendService
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import com.kotlin.developer.demo.kafka.util.Constants.Companion.TOPIC_USER
import org.slf4j.LoggerFactory

@Service
class KafkaSendServiceImpl (private val kafkaTemplate: KafkaTemplate<String, User>?)
    : KafkaSendService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun sendMessage(user: User) {
        log.info("send: {}", user.toString())
        val response = kafkaTemplate!!.send(TOPIC_USER, user)
    }
}
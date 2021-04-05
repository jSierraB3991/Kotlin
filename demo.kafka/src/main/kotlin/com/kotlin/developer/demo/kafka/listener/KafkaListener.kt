package com.kotlin.developer.demo.kafka.listener

import com.kotlin.developer.demo.kafka.model.User
import com.kotlin.developer.demo.kafka.service.KafkaListenerService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import com.kotlin.developer.demo.kafka.util.Constants.Companion.TOPIC_USER
import org.slf4j.LoggerFactory

@Service
class KafkaListener( private val kafkaListenerService: KafkaListenerService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [TOPIC_USER], containerFactory = "config.kafka.demo.kotlin")
    fun listenMessage(user: User){
        kafkaListenerService!!.listenerMessage(user)
        logger.info("user with user name: ${user.userName} received")
    }
}
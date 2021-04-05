package com.kotlin.developer.demo.kafka.service

import com.kotlin.developer.demo.kafka.model.User

interface KafkaSendService {
    fun sendMessage(user: User)
}
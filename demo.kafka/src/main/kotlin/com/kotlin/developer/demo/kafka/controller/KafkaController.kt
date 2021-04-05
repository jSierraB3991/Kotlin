package com.kotlin.developer.demo.kafka.controller

import com.kotlin.developer.demo.kafka.model.User
import com.kotlin.developer.demo.kafka.service.KafkaSendService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class KafkaController(private val kafkaSendService: KafkaSendService?) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/message")
    fun sendMessage(): String{
        return try{
            kafkaSendService!!.sendMessage(User("juan", "123456789"))
            return "proceso exitoso"
        }
        catch(e: Exception){
            log.error(e.message)
            e!!.message!!
        }
    }
}
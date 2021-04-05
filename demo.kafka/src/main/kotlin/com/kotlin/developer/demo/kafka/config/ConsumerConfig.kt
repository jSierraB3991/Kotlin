package com.kotlin.developer.demo.kafka.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlin.developer.demo.kafka.model.User
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class ConsumerConfig(private val objectMapper: ObjectMapper)  {

    @Bean(name = ["config.kafka.demo.kotlin"])
    fun productKafkaListenerContainerFactory(kafkaProperties: KafkaProperties): ConcurrentKafkaListenerContainerFactory<String, User> {
        val containerFactory: ConcurrentKafkaListenerContainerFactory<String, User> = ConcurrentKafkaListenerContainerFactory<String, User>()
        val errorHandlingDeserializer = ErrorHandlingDeserializer(JsonDeserializer(User::class.java, objectMapper))
        val consumerFactory: DefaultKafkaConsumerFactory<String, User> = DefaultKafkaConsumerFactory(
                KafkaConfig.getConfig(kafkaProperties),
                StringDeserializer(),
                errorHandlingDeserializer
        )
        containerFactory.consumerFactory = consumerFactory
        return containerFactory
    }
}
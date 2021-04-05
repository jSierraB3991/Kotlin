package com.kotlin.developer.demo.kafka.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig(
        private val objectMapper: ObjectMapper,
        private val kafkaConfig: KafkaConfig
) {

    @Bean
    fun <T> kafkaTemplate(factory: ProducerFactory<String, T>): KafkaTemplate<String, T> {
        return KafkaTemplate(factory)
    }

    @Bean
    fun <T> producerFactory(kafkaProperties: KafkaProperties): ProducerFactory<String, T> {
        val valueSerializer: JsonSerializer<T> = JsonSerializer(objectMapper)
        return DefaultKafkaProducerFactory(
                KafkaConfig.getConfig(kafkaProperties),
                StringSerializer(),
                valueSerializer.noTypeInfo()
        )
    }
}

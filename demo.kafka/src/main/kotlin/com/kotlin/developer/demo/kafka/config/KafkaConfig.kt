package com.kotlin.developer.demo.kafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@Configuration
@EnableKafka
class KafkaConfig {
    companion object {
        fun getConfig(kafkaProperties: KafkaProperties): Map<String, Any> {
            return mapOf(
                    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
                    ConsumerConfig.GROUP_ID_CONFIG to kafkaProperties.consumer.groupId,
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers
            )
        }
    }
}
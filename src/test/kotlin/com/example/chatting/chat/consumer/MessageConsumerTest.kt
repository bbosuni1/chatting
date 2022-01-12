package com.example.chatting.chat.consumer

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MessageConsumerTest(
    private var kafkaProducer: KafkaProducer<String, String>,
    private var kafkaConsumer: KafkaConsumer<String, String>
){

    @BeforeEach
    fun setUp() {
        val producerRecord :ProducerRecord<String, String> = ProducerRecord("new-topic", "test")
        kafkaProducer.send(producerRecord)
    }

    @Test
    fun consume() {
        kafkaConsumer.subscribe(listOf("new-topic"))

    }

}
package com.example.chatting.chat.consumer

import com.example.chatting.chat.controller.KAFKA_TOPIC
import com.example.chatting.chat.domain.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class MessageConsumer(
    private var template: SimpMessagingTemplate
) {

    val log: Logger = LoggerFactory.getLogger(MessageConsumer::class.java)

    @KafkaListener(topics = [KAFKA_TOPIC], groupId = "chatting")
    fun consume(@Headers headers: MessageHeaders, @Payload message: Message) {
        log.info("Headers: $headers")
        log.info("Kafka Consume Message: ${message.text}, Author: ${message.author}")
        template.convertAndSend("/topic/group", message);
    }

}
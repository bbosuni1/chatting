package com.example.chatting.chat.controller

import com.example.chatting.chat.domain.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

const val KAFKA_TOPIC: String = "new-kafka-chat"

@RestController
class ChatController(
    private var kafkaTemplate: KafkaTemplate<String, Message>
) {
    val log: Logger = LoggerFactory.getLogger(ChatController::class.java)

    @PostMapping("/chat/send")
    fun sendMessage(@RequestBody message: Message) {
        message.createTimestamp()
        log.info("message : ${message.text}, auth : ${message.author}")
        kafkaTemplate.send(KAFKA_TOPIC, message)
        // post 요청으로 전달받은 메시지를 해당 카프카 토픽에 생산
    }

}
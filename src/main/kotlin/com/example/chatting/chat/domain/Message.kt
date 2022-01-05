package com.example.chatting.chat.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class Message(
    @JsonProperty("author") val author: String?,
    @JsonProperty("text") val text: String?,
    var timestamp: LocalDateTime?
) {
    fun createTimestamp() {
        this.timestamp = LocalDateTime.now()
    }
}
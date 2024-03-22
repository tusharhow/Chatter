package com.tusharhow.chatter.model


import com.tusharhow.chatter.types.MessageStatus
import com.tusharhow.chatter.types.MessageTypes

data class Message(
    val message: String,
    val messageType: MessageTypes,
    val isUser: Boolean,
    val status: MessageStatus?,
    val time: String
)

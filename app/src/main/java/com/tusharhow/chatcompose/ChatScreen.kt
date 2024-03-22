package com.tusharhow.chatcompose

import com.tusharhow.chatter.Chatter
import com.tusharhow.chatter.model.Message
import com.tusharhow.chatter.types.MessageStatus
import com.tusharhow.chatter.types.MessageTypes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    val messageState = remember { mutableStateOf("") }
    val messages = remember { mutableStateOf<List<Message>>(listOf()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Sebastian")
                }
            )
        }
    ) {
        it
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(messages.value.size) { index ->
                    Chatter(
                        message = messages.value[index].message,
                        isSender = messages.value[index].isUser,
                        messageType = messages.value[index].messageType,
                        status = messages.value[index].status,

                        )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = messageState.value,
                    onValueChange = { messageState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    placeholder = {
                        Text(
                            text = "Type a message",
                            color = Color.Gray
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Button(onClick = {
                    val updatedMessages = sendMessage(messageState.value, messages.value)
                    messages.value = updatedMessages
                    messageState.value = ""
                }) {
                    Text(text = "Send")
                }
            }

        }
    }
}


fun sendMessage(message: String, messages: List<Message>): List<Message> {
    val newMessages = messages.toMutableList()
    newMessages.add(
        Message(
            message = message,
            messageType = MessageTypes.IMAGE,
            isUser = newMessages.size % 2 == 0,
            status = MessageStatus.SENT,
            time = "12:00"
        )
    )
    return newMessages
}

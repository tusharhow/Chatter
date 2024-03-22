package com.tusharhow.chatter

import android.text.format.DateFormat
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tusharhow.chatter.types.MessageStatus
import com.tusharhow.chatter.types.MessageTypes
import java.sql.Timestamp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Chatter(
    modifier: Modifier = Modifier,
    message: Any,
    messageStyle: TextStyle = TextStyle.Default,
    isSender: Boolean = true,
    userAvatar: String = "",
    status: MessageStatus? = MessageStatus.SENT,
    messageType: MessageTypes = MessageTypes.VIDEO,
    timestamp: Timestamp = Timestamp(System.currentTimeMillis()),
    timeStyle: TextStyle = TextStyle(
        color = Color.Gray,
        fontSize = 10.sp
    ),
    bubbleColor: Color = Color(if (isSender) 0xFFE1F5FE else 0xFFE0E0E0),
    readContent: () -> Unit = {},
    bubbleShape: RoundedCornerShape = RoundedCornerShape(
        topStart = if (isSender) 16.dp else 16.dp,
        topEnd = if (isSender) 16.dp else 16.dp,
        bottomEnd = if (isSender) 0.dp else 16.dp,
        bottomStart = if (isSender) 16.dp else 0.dp
    ),
    onLongPress: () -> Unit = {},
    onPress: () -> Unit = {},

    ) {
    val alignment = if (isSender) Alignment.CenterEnd else Alignment.CenterStart
    // hide format time in production
    val formatTime = DateFormat.format("hh:mm", timestamp)

    when (messageType) {
        MessageTypes.TEXT -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start
            ) {
                if (!isSender) {
                    Box(
                        modifier = Modifier

                            .size(30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray)
                    ) {
                        Text(
                            text = "S",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Box(
                    modifier = modifier
                        .padding(
                            16.dp,
                        )
                        .clip(bubbleShape)
                        .background(bubbleColor)
                        .combinedClickable(
                            onLongClick = onLongPress,
                            onClick = onPress
                        ),
                    contentAlignment = alignment
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)

                    ) {
                        Text(
                            text = message.toString(),
                            style = messageStyle,
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.Bottom),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)

                        ) {
                            Text(
                                text = formatTime.toString(),
                                style = timeStyle,
                                modifier = Modifier.align(Alignment.Bottom)
                            )
                            status?.let {
                                Image(
                                    painter = painterResource(id = R.drawable.check),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.Gray),
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }

                }
                if (isSender) {
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray)
                    ) {
                        Text(
                            text = "S",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }

        MessageTypes.IMAGE -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start
            ) {
                if (!isSender) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray)
                    ) {
                        Text(
                            text = "S",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Box(
                    modifier = modifier
                        .padding(8.dp)
                        .wrapContentSize()
                        .clip(bubbleShape)
                        .background(bubbleColor)
                        .combinedClickable(
                            onLongClick = onLongPress,
                            onClick = onPress
                        ),
                    contentAlignment = alignment
                ) {
                    AsyncImage(
                        model = "https://www.scusd.edu/sites/main/files/main-images/camera_lense_0.jpeg",
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(150.dp),


                        contentScale = ContentScale.Fit,

                        )

                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)

                    ) {
                        Text(
                            text = formatTime.toString(),
                            style = timeStyle,
                            modifier = Modifier.align(Alignment.Bottom)
                        )
                        status?.let {
                            Image(
                                painter = painterResource(id = R.drawable.check),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color.Gray),
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                }
                if (isSender) {
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray)
                    ) {
                        Text(
                            text = "S",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

        }

        MessageTypes.VIDEO -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),

                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start
            ) {
                // user avatar position according to the user
                if (!isSender) {
                    Box(
                        modifier = Modifier

                            .size(30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray)
                    ) {
                        Text(
                            text = "S",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                Box(
                    modifier = modifier
                        .padding(
                            16.dp,
                        )
                        .clip(bubbleShape)
                        .background(bubbleColor)
                        .combinedClickable(
                            onLongClick = onLongPress,
                            onClick = onPress
                        ),
                    contentAlignment = alignment
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)

                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Video",
                            modifier = Modifier.size(100.dp),
                            colorFilter = ColorFilter.tint(Color.Black)
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.Bottom),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)

                        ) {
                            Text(
                                text = formatTime.toString(),
                                style = timeStyle,
                                modifier = Modifier.align(Alignment.Bottom)
                            )
                            status?.let {
                                Image(
                                    painter = painterResource(id = R.drawable.check),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.Gray),
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }

                }
                if (isSender) {
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray)
                    ) {
                        Text(
                            text = "S",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }

        MessageTypes.AUDIO -> {
            Box(
                modifier = modifier
                    .padding(8.dp)
                    .wrapContentSize()
                    .clip(bubbleShape)
                    .background(bubbleColor)
                    .combinedClickable(
                        onLongClick = onLongPress,
                        onClick = onPress
                    ),
                contentAlignment = alignment
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Audio",
                    modifier = Modifier.size(100.dp),
                    colorFilter = ColorFilter.tint(Color.Black)
                )
                Text(
                    text = formatTime.toString(),
                    style = timeStyle,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                )
            }
        }
    }
}
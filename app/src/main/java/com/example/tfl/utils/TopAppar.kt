package com.example.tfl.utils

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.example.tfl.R

@Composable
fun TopBarFrame(
    onBackClick: () -> Unit = {},
    onBackIcon: Int = R.drawable.baseline_keyboard_arrow_down_24,
    text: String = "Timesheet",
    onCloseClick: () -> Unit = {},
    onCloseIcon: Int = R.drawable.baseline_keyboard_arrow_up_24,
    showBackIcon: Boolean = true,
    showCloseIcon: Boolean = true,
    backgroundColor: Color = colorScheme.onSecondaryContainer,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp)
            .background(color = backgroundColor)
            .padding(horizontal = 4.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showBackIcon) {
                IconButton(
                    onClick = { onBackClick() },
                    modifier = Modifier.size(size = 48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = onBackIcon),
                        contentDescription = "Icons/arrow_back_24px",
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = text,
                        style = TextStyle(
                            fontSize = 22.sp,
                            lineHeight = 28.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (showCloseIcon) {
                        IconButton(
                            onClick = { onCloseClick() },
                            modifier = Modifier.size(size = 48.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = onCloseIcon),
                                contentDescription = "Icons/close",
                                tint = Color(0xFFFFFFFF)
                            )
                        }
                    }
                }
            }
}

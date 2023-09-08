package com.example.tfl.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.*
import com.example.tfl.data.model.*
import com.example.tfl.utils.*

@Composable
fun TubeLineStatusScreen() {
    val viewModel: TubeLineViewModel = viewModel()
    val tubeLineStatusState by viewModel.tubeLineStatusState.collectAsState()

    when (tubeLineStatusState) {
        is TubeLineStatusState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .size(50.dp)
            )
        }

        is TubeLineStatusState.Success -> {
            val tubeLineStatus = (tubeLineStatusState as TubeLineStatusState.Success).data
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)
            ) {
                items(tubeLineStatus) { line ->
                    TubeLineStatusCard(line)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        is TubeLineStatusState.Error -> {
            val errorMessage = (tubeLineStatusState as TubeLineStatusState.Error).message
            Text(
                text = errorMessage,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                color = Color.Red
            )
        }
    }
}

@Composable
fun TubeLineStatusCard(line: TubeLineStatus) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(getColorForLine(line.name), CircleShape)
                        .clip(CircleShape)
                        .size(24.dp),
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = line.name ?: "Unknown",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier.clickable { expanded = !expanded })
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                line.lineStatuses?.let { statuses ->
                    statuses.forEach { status ->
                        Text(
                            text = "Status: ${status.statusSeverityDescription ?: "Unknown"}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "Reason: ${status.reason ?: "No reason available"}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

private fun getColorForLine(lineName: String?): Color {
    return when (lineName) {
        "Bakerloo" -> Color(0xFFB36305) // Brown
        "Central" -> Color(0xFFE32017) // Red
        "Circle" -> Color(0xFFFFD300) // Yellow
        "District" -> Color(0xFF007229) // Green
        "Hammersmith & City" -> Color(0xFFF86C67) // Pink
        "Jubilee" -> Color(0xFFA0A5A9) // Silver/Grey
        "Metropolitan" -> Color(0xFF8A004F) // Purple
        "Northern" -> Color(0xFF000000) // Black
        "Piccadilly" -> Color(0xFF0019A8) // Dark Blue
        "Victoria" -> Color(0xFF00A0E2) // Light Blue
        "Waterloo & City" -> Color(0xFF00A99D) // Turquoise
        else -> Color.Gray
    }
}
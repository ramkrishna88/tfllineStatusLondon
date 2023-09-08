package com.example.tfl.data.model

data class TubeLineStatus(
    val id: String? = null,
    val name: String? = null,
    val lineStatuses: List<LineStatus>? = null,
)

data class LineStatus(
    val id: Int? = null,
    val statusSeverityDescription: String? = null,
    val reason: String? = null,
)



package com.akaokatubasa.silenthub.ui.screens.time

import com.akaokatubasa.silenthub.data.model.NotificationItem

data class TimeUiState(
    val notifications: List<NotificationItem> = emptyList(),
    val isLoading: Boolean = true
)

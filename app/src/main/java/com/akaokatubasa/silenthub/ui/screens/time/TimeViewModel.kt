package com.akaokatubasa.silenthub.ui.screens.time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akaokatubasa.silenthub.data.model.NotificationItem
import com.akaokatubasa.silenthub.domain.usecase.AddNotificationUseCase
import com.akaokatubasa.silenthub.domain.usecase.DeleteNotificationUseCase
import com.akaokatubasa.silenthub.domain.usecase.GetNotificationsUseCase
import com.akaokatubasa.silenthub.domain.usecase.ScheduleNotificationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class TimeViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val addNotificationUseCase: AddNotificationUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    private val scheduleNotificationUseCase: ScheduleNotificationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TimeUiState())
    val uiState: StateFlow<TimeUiState> = _uiState.asStateFlow()

    init {
        reload()
    }

    fun scheduleTestFiveSeconds() {
        val item = NotificationItem(
            id = UUID.randomUUID().toString(),
            title = "SilentHub test",
            time = System.currentTimeMillis() + FIVE_SECONDS_MS
        )
        viewModelScope.launch {
            addNotificationUseCase(item)
            scheduleNotificationUseCase(item)
            reload()
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            deleteNotificationUseCase(id)
            reload()
        }
    }

    private fun reload() {
        viewModelScope.launch {
            val notifications = getNotificationsUseCase()
            _uiState.value = TimeUiState(
                notifications = notifications,
                isLoading = false
            )
        }
    }

    private companion object {
        const val FIVE_SECONDS_MS = 5_000L
    }
}

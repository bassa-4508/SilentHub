package com.akaokatubasa.silenthub.ui.screens.time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akaokatubasa.silenthub.domain.usecase.AddNotificationUseCase
import com.akaokatubasa.silenthub.domain.usecase.DeleteNotificationUseCase
import com.akaokatubasa.silenthub.domain.usecase.GetNotificationsUseCase
import com.akaokatubasa.silenthub.domain.usecase.ScheduleNotificationUseCase

class TimeViewModelFactory(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val addNotificationUseCase: AddNotificationUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    private val scheduleNotificationUseCase: ScheduleNotificationUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TimeViewModel(
            getNotificationsUseCase = getNotificationsUseCase,
            addNotificationUseCase = addNotificationUseCase,
            deleteNotificationUseCase = deleteNotificationUseCase,
            scheduleNotificationUseCase = scheduleNotificationUseCase
        ) as T
    }
}

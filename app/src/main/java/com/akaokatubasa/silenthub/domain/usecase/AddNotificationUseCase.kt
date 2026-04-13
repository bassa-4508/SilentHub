package com.akaokatubasa.silenthub.domain.usecase

import com.akaokatubasa.silenthub.data.model.NotificationItem
import com.akaokatubasa.silenthub.data.repository.SilentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddNotificationUseCase(private val repository: SilentRepository) {

    suspend operator fun invoke(item: NotificationItem) = withContext(Dispatchers.IO) {
        repository.addNotification(item)
    }
}

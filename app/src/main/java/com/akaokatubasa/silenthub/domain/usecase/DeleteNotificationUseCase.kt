package com.akaokatubasa.silenthub.domain.usecase

import com.akaokatubasa.silenthub.data.repository.SilentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteNotificationUseCase(private val repository: SilentRepository) {

    suspend operator fun invoke(id: String) = withContext(Dispatchers.IO) {
        repository.deleteNotification(id)
    }
}

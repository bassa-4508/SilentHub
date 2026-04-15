package com.akaokatubasa.silenthub.domain.usecase

import com.akaokatubasa.silenthub.data.repository.SilentRepository
import com.akaokatubasa.silenthub.domain.scheduler.NotificationScheduler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CancelNotificationUseCase(
    private val repository: SilentRepository,
    private val scheduler: NotificationScheduler
) {
    // JSON削除とAlarmキャンセルは必ずセット — claude.md §17「データの安全性 > UIの一貫性」
    suspend operator fun invoke(id: String) = withContext(Dispatchers.IO) {
        try {
            repository.deleteNotification(id)
        } catch (t: Throwable) {
            // fall through: we still want the alarm cancelled
        }
        try {
            scheduler.cancel(id)
        } catch (t: Throwable) {
            // claude.md §9
        }
    }
}

package com.akaokatubasa.silenthub.domain.usecase

import com.akaokatubasa.silenthub.data.model.NotificationItem
import com.akaokatubasa.silenthub.domain.scheduler.NotificationScheduler

class ScheduleNotificationUseCase(
    private val scheduler: NotificationScheduler
) {
    operator fun invoke(item: NotificationItem) {
        scheduler.schedule(item)
    }
}

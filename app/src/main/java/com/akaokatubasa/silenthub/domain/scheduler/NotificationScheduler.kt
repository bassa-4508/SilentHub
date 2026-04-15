package com.akaokatubasa.silenthub.domain.scheduler

import com.akaokatubasa.silenthub.data.model.NotificationItem

interface NotificationScheduler {
    fun schedule(item: NotificationItem)
    fun cancel(id: String)
}

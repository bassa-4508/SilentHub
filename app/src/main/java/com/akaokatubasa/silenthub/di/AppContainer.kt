package com.akaokatubasa.silenthub.di

import android.content.Context
import com.akaokatubasa.silenthub.data.datasource.JsonDataSource
import com.akaokatubasa.silenthub.data.repository.SilentRepository
import com.akaokatubasa.silenthub.domain.scheduler.NotificationScheduler
import com.akaokatubasa.silenthub.domain.usecase.AddContactUseCase
import com.akaokatubasa.silenthub.domain.usecase.AddNotificationUseCase
import com.akaokatubasa.silenthub.domain.usecase.CancelNotificationUseCase
import com.akaokatubasa.silenthub.domain.usecase.DeleteContactUseCase
import com.akaokatubasa.silenthub.domain.usecase.ExportDataUseCase
import com.akaokatubasa.silenthub.domain.usecase.GetContactsUseCase
import com.akaokatubasa.silenthub.domain.usecase.GetNotificationsUseCase
import com.akaokatubasa.silenthub.domain.usecase.ImportDataUseCase
import com.akaokatubasa.silenthub.domain.usecase.ScheduleNotificationUseCase
import com.akaokatubasa.silenthub.notification.AlarmNotificationScheduler

class AppContainer(context: Context) {

    private val dataSource: JsonDataSource = JsonDataSource(context)
    private val repository: SilentRepository = SilentRepository(dataSource)
    private val notificationScheduler: NotificationScheduler = AlarmNotificationScheduler(context)

    val getContactsUseCase: GetContactsUseCase = GetContactsUseCase(repository)
    val addContactUseCase: AddContactUseCase = AddContactUseCase(repository)
    val deleteContactUseCase: DeleteContactUseCase = DeleteContactUseCase(repository)
    val getNotificationsUseCase: GetNotificationsUseCase = GetNotificationsUseCase(repository)
    val addNotificationUseCase: AddNotificationUseCase = AddNotificationUseCase(repository)
    val scheduleNotificationUseCase: ScheduleNotificationUseCase =
        ScheduleNotificationUseCase(notificationScheduler)
    val cancelNotificationUseCase: CancelNotificationUseCase =
        CancelNotificationUseCase(repository, notificationScheduler)
    val exportDataUseCase: ExportDataUseCase = ExportDataUseCase(repository)
    val importDataUseCase: ImportDataUseCase = ImportDataUseCase(repository)
}

package com.akaokatubasa.silenthub.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.akaokatubasa.silenthub.data.model.NotificationItem
import com.akaokatubasa.silenthub.domain.scheduler.NotificationScheduler

class AlarmNotificationScheduler(private val context: Context) : NotificationScheduler {

    private val alarmManager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun schedule(item: NotificationItem) {
        if (!item.enabled) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
            !alarmManager.canScheduleExactAlarms()
        ) return

        val pendingIntent = buildPendingIntent(item)
        val clockInfo = AlarmManager.AlarmClockInfo(item.time, pendingIntent)
        try {
            alarmManager.setAlarmClock(clockInfo, pendingIntent)
        } catch (t: SecurityException) {
            // Exact alarm permission revoked at runtime; fail silent per claude.md §9
        }
    }

    override fun cancel(id: String) {
        val pendingIntent = buildCancelPendingIntent(id)
        try {
            alarmManager.cancel(pendingIntent)
            pendingIntent.cancel()
        } catch (t: Throwable) {
            // claude.md §9 — never crash on cancel
        }
    }

    private fun buildPendingIntent(item: NotificationItem): PendingIntent {
        val intent = Intent(context, NotificationAlarmReceiver::class.java).apply {
            putExtra(NotificationAlarmReceiver.EXTRA_ID, item.id)
            putExtra(NotificationAlarmReceiver.EXTRA_TITLE, item.title)
        }
        return PendingIntent.getBroadcast(
            context,
            item.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    // Intent.filterEquals ignores extras — component + requestCode are enough
    // to match the PendingIntent registered by schedule().
    private fun buildCancelPendingIntent(id: String): PendingIntent {
        val intent = Intent(context, NotificationAlarmReceiver::class.java)
        return PendingIntent.getBroadcast(
            context,
            id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}

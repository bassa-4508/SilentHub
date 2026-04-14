package com.akaokatubasa.silenthub.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat

object NotificationChannels {

    const val SILENT_ALERTS_ID = "silent_alerts"

    fun ensureCreated(context: Context) {
        val manager = NotificationManagerCompat.from(context)
        if (manager.getNotificationChannel(SILENT_ALERTS_ID) != null) return

        val channel = NotificationChannel(
            SILENT_ALERTS_ID,
            "Silent Alerts",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Silent time notifications without sound or vibration"
            setSound(null, null)
            enableVibration(false)
            enableLights(false)
            setBypassDnd(false)
        }
        manager.createNotificationChannel(channel)
    }
}

package com.akaokatubasa.silenthub.notification

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akaokatubasa.silenthub.ui.theme.NotificationBackground
import com.akaokatubasa.silenthub.ui.theme.NotificationForeground
import com.akaokatubasa.silenthub.ui.theme.SilentHubTheme

class FullScreenNotificationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyFullScreenFlags()

        val title = intent.getStringExtra(EXTRA_TITLE).orEmpty()

        setContent {
            SilentHubTheme {
                FullScreenNotificationContent(
                    title = title,
                    onDismiss = { finish() }
                )
            }
        }
    }

    private fun applyFullScreenFlags() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguard = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguard.requestDismissKeyguard(this, null)
        } else {
            @Suppress("DEPRECATION")
            window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
            )
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
    }
}

@Composable
private fun FullScreenNotificationContent(
    title: String,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NotificationBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = title.ifBlank { "SilentHub" },
                style = MaterialTheme.typography.displayLarge,
                color = NotificationForeground
            )
            Spacer(Modifier.height(48.dp))
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Dismiss",
                    style = MaterialTheme.typography.labelLarge,
                    color = NotificationForeground
                )
            }
        }
    }
}

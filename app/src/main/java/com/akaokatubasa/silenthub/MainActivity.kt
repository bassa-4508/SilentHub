package com.akaokatubasa.silenthub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.akaokatubasa.silenthub.di.AppContainer
import com.akaokatubasa.silenthub.notification.NotificationChannels
import com.akaokatubasa.silenthub.ui.navigation.SilentHubBottomBar
import com.akaokatubasa.silenthub.ui.navigation.SilentHubNavHost
import com.akaokatubasa.silenthub.ui.screens.contacts.ContactsViewModelFactory
import com.akaokatubasa.silenthub.ui.screens.time.TimeViewModelFactory
import com.akaokatubasa.silenthub.ui.theme.SilentHubTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        NotificationChannels.ensureCreated(applicationContext)

        val container = AppContainer(applicationContext)
        val contactsFactory = ContactsViewModelFactory(container.getContactsUseCase)
        val timeFactory = TimeViewModelFactory(
            getNotificationsUseCase = container.getNotificationsUseCase,
            addNotificationUseCase = container.addNotificationUseCase,
            deleteNotificationUseCase = container.deleteNotificationUseCase,
            scheduleNotificationUseCase = container.scheduleNotificationUseCase
        )

        setContent {
            SilentHubApp(
                contactsViewModelFactory = contactsFactory,
                timeViewModelFactory = timeFactory
            )
        }
    }
}

@Composable
private fun SilentHubApp(
    contactsViewModelFactory: ContactsViewModelFactory,
    timeViewModelFactory: TimeViewModelFactory
) {
    SilentHubTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { SilentHubBottomBar(navController = navController) }
        ) { innerPadding ->
            SilentHubNavHost(
                navController = navController,
                contactsViewModelFactory = contactsViewModelFactory,
                timeViewModelFactory = timeViewModelFactory,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

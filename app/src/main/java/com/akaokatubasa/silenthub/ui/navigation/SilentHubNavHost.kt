package com.akaokatubasa.silenthub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akaokatubasa.silenthub.ui.screens.contacts.ContactsScreen
import com.akaokatubasa.silenthub.ui.screens.settings.SettingsScreen
import com.akaokatubasa.silenthub.ui.screens.time.TimeScreen

@Composable
fun SilentHubNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Time.route,
        modifier = modifier
    ) {
        composable(Destination.Time.route) { TimeScreen() }
        composable(Destination.Contacts.route) { ContactsScreen() }
        composable(Destination.Settings.route) { SettingsScreen() }
    }
}

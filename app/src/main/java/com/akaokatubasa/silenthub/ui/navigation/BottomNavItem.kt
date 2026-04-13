package com.akaokatubasa.silenthub.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.akaokatubasa.silenthub.R

data class BottomNavItem(
    val destination: Destination,
    @StringRes val labelRes: Int,
    val icon: ImageVector
)

val bottomNavItems: List<BottomNavItem> = listOf(
    BottomNavItem(Destination.Time, R.string.nav_time, Icons.Filled.DateRange),
    BottomNavItem(Destination.Contacts, R.string.nav_contacts, Icons.Filled.Person),
    BottomNavItem(Destination.Settings, R.string.nav_settings, Icons.Filled.Settings)
)

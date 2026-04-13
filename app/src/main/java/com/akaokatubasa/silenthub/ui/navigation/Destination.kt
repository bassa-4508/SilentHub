package com.akaokatubasa.silenthub.ui.navigation

sealed class Destination(val route: String) {
    data object Time : Destination("time")
    data object Contacts : Destination("contacts")
    data object Settings : Destination("settings")
}

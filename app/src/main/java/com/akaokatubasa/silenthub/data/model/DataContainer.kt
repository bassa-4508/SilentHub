package com.akaokatubasa.silenthub.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DataContainer(
    val contacts: List<Contact> = emptyList(),
    val notifications: List<NotificationItem> = emptyList()
)

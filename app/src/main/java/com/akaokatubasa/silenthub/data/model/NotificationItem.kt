package com.akaokatubasa.silenthub.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NotificationItem(
    val id: String,
    val title: String,
    val time: Long,
    val contactId: String? = null,
    val enabled: Boolean = true
)

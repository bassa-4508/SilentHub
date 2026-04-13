package com.akaokatubasa.silenthub.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id: String,
    val name: String,
    val type: String,
    val value: String,
    val tags: List<String> = emptyList()
)

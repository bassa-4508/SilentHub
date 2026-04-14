package com.akaokatubasa.silenthub.ui.screens.contacts

import com.akaokatubasa.silenthub.data.model.Contact

data class ContactsUiState(
    val contacts: List<Contact> = emptyList(),
    val isLoading: Boolean = true
)

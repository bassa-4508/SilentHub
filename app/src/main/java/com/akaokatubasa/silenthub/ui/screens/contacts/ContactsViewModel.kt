package com.akaokatubasa.silenthub.ui.screens.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akaokatubasa.silenthub.data.model.Contact
import com.akaokatubasa.silenthub.domain.usecase.AddContactUseCase
import com.akaokatubasa.silenthub.domain.usecase.DeleteContactUseCase
import com.akaokatubasa.silenthub.domain.usecase.GetContactsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ContactsViewModel(
    private val getContactsUseCase: GetContactsUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactsUiState())
    val uiState: StateFlow<ContactsUiState> = _uiState.asStateFlow()

    init {
        reload()
    }

    fun addContact(name: String) {
        val trimmed = name.trim()
        if (trimmed.isEmpty()) return
        val contact = Contact(
            id = UUID.randomUUID().toString(),
            name = trimmed,
            type = "",
            value = ""
        )
        viewModelScope.launch {
            addContactUseCase(contact)
            reload()
        }
    }

    fun deleteContact(id: String) {
        viewModelScope.launch {
            deleteContactUseCase(id)
            reload()
        }
    }

    private fun reload() {
        viewModelScope.launch {
            val contacts = getContactsUseCase()
            _uiState.value = ContactsUiState(
                contacts = contacts,
                isLoading = false
            )
        }
    }
}

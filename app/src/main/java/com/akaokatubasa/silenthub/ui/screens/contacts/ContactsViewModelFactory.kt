package com.akaokatubasa.silenthub.ui.screens.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akaokatubasa.silenthub.domain.usecase.AddContactUseCase
import com.akaokatubasa.silenthub.domain.usecase.DeleteContactUseCase
import com.akaokatubasa.silenthub.domain.usecase.GetContactsUseCase

class ContactsViewModelFactory(
    private val getContactsUseCase: GetContactsUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(
            getContactsUseCase = getContactsUseCase,
            addContactUseCase = addContactUseCase,
            deleteContactUseCase = deleteContactUseCase
        ) as T
    }
}

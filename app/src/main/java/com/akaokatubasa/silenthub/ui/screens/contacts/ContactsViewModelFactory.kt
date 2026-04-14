package com.akaokatubasa.silenthub.ui.screens.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akaokatubasa.silenthub.domain.usecase.GetContactsUseCase

class ContactsViewModelFactory(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(getContactsUseCase) as T
    }
}

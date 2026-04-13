package com.akaokatubasa.silenthub.domain.usecase

import com.akaokatubasa.silenthub.data.model.Contact
import com.akaokatubasa.silenthub.data.repository.SilentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetContactsUseCase(private val repository: SilentRepository) {

    suspend operator fun invoke(): List<Contact> = withContext(Dispatchers.IO) {
        repository.getContacts()
    }
}

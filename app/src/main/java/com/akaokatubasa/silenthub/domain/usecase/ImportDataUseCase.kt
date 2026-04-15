package com.akaokatubasa.silenthub.domain.usecase

import com.akaokatubasa.silenthub.data.model.DataContainer
import com.akaokatubasa.silenthub.data.repository.SilentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ImportDataUseCase(private val repository: SilentRepository) {

    private val json: Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    // Decode first (validation); if it fails, keep existing data untouched.
    suspend operator fun invoke(raw: String) = withContext(Dispatchers.IO) {
        try {
            val data = json.decodeFromString(DataContainer.serializer(), raw)
            repository.replaceAll(data)
        } catch (t: Throwable) {
            // claude.md §9 — invalid/garbage JSON must not clobber state
        }
    }
}

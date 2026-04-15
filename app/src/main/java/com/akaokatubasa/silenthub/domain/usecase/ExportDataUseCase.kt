package com.akaokatubasa.silenthub.domain.usecase

import com.akaokatubasa.silenthub.data.model.DataContainer
import com.akaokatubasa.silenthub.data.repository.SilentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ExportDataUseCase(private val repository: SilentRepository) {

    private val json: Json = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    suspend operator fun invoke(): String = withContext(Dispatchers.IO) {
        try {
            val data = repository.getRaw()
            json.encodeToString(DataContainer.serializer(), data)
        } catch (t: Throwable) {
            // claude.md §9 — never crash. Emit empty container as fallback.
            json.encodeToString(DataContainer.serializer(), DataContainer())
        }
    }
}

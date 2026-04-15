package com.akaokatubasa.silenthub.data.datasource

import android.content.Context
import com.akaokatubasa.silenthub.data.model.DataContainer
import kotlinx.serialization.json.Json
import java.io.File

class JsonDataSource(context: Context) {

    private val file: File = File(context.filesDir, FILE_NAME)

    private val json: Json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    fun load(): DataContainer {
        return try {
            if (!file.exists()) {
                val empty = DataContainer()
                save(empty)
                return empty
            }
            val text = file.readText()
            if (text.isBlank()) {
                DataContainer()
            } else {
                json.decodeFromString(DataContainer.serializer(), text)
            }
        } catch (t: Throwable) {
            DataContainer()
        }
    }

    fun save(data: DataContainer) {
        try {
            writeRaw(json.encodeToString(DataContainer.serializer(), data))
        } catch (t: Throwable) {
            // swallow: persistence failures must not crash the app
        }
    }

    fun writeRaw(rawJson: String) {
        try {
            file.writeText(rawJson)
        } catch (t: Throwable) {
            // claude.md §9
        }
    }

    companion object {
        private const val FILE_NAME = "data.json"
    }
}

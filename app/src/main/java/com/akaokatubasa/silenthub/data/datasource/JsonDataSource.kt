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
                writeToFile(empty)
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
            writeToFile(data)
        } catch (t: Throwable) {
            // swallow: persistence failures must not crash the app
        }
    }

    private fun writeToFile(data: DataContainer) {
        val text = json.encodeToString(DataContainer.serializer(), data)
        file.writeText(text)
    }

    companion object {
        private const val FILE_NAME = "data.json"
    }
}

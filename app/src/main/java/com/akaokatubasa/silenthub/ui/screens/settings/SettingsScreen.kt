package com.akaokatubasa.silenthub.ui.screens.settings

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akaokatubasa.silenthub.R

@Composable
fun SettingsScreen(
    factory: SettingsViewModelFactory,
    modifier: Modifier = Modifier
) {
    val viewModel: SettingsViewModel = viewModel(factory = factory)
    val context = LocalContext.current
    val pendingExportJson = remember { mutableStateOf<String?>(null) }

    val createDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri: Uri? ->
        val json = pendingExportJson.value
        pendingExportJson.value = null
        if (uri == null || json == null) return@rememberLauncherForActivityResult
        try {
            context.contentResolver.openOutputStream(uri)?.use { stream ->
                stream.write(json.toByteArray(Charsets.UTF_8))
            }
        } catch (t: Throwable) {
            // claude.md §9 — persistence failures must not crash
        }
    }

    val openDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult
        val raw: String? = try {
            context.contentResolver.openInputStream(uri)?.use { stream ->
                stream.readBytes().toString(Charsets.UTF_8)
            }
        } catch (t: Throwable) {
            null
        }
        if (raw != null) {
            viewModel.import(raw)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.nav_settings),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Backup & Restore",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.export { json ->
                    pendingExportJson.value = json
                    try {
                        createDocumentLauncher.launch(DEFAULT_EXPORT_FILENAME)
                    } catch (t: Throwable) {
                        pendingExportJson.value = null
                    }
                }
            }
        ) {
            Text(text = "Export")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    openDocumentLauncher.launch(arrayOf("application/json"))
                } catch (t: Throwable) {
                    // claude.md §9
                }
            }
        ) {
            Text(text = "Import")
        }
    }
}

private const val DEFAULT_EXPORT_FILENAME = "silenthub_backup.json"

package com.akaokatubasa.silenthub.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akaokatubasa.silenthub.domain.usecase.ExportDataUseCase
import com.akaokatubasa.silenthub.domain.usecase.ImportDataUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val exportDataUseCase: ExportDataUseCase,
    private val importDataUseCase: ImportDataUseCase
) : ViewModel() {

    fun export(onReady: (String) -> Unit) {
        viewModelScope.launch {
            val json = try {
                exportDataUseCase()
            } catch (t: Throwable) {
                return@launch
            }
            onReady(json)
        }
    }

    fun import(raw: String) {
        viewModelScope.launch {
            importDataUseCase(raw)
        }
    }
}

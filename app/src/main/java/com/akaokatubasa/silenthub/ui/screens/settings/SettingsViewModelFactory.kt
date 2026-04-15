package com.akaokatubasa.silenthub.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akaokatubasa.silenthub.domain.usecase.ExportDataUseCase
import com.akaokatubasa.silenthub.domain.usecase.ImportDataUseCase

class SettingsViewModelFactory(
    private val exportDataUseCase: ExportDataUseCase,
    private val importDataUseCase: ImportDataUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(
            exportDataUseCase = exportDataUseCase,
            importDataUseCase = importDataUseCase
        ) as T
    }
}

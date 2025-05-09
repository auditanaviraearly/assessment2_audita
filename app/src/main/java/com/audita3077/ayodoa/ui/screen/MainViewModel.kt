package com.audita3077.ayodoa.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audita3077.ayodoa.database.DoaDao
import com.audita3077.ayodoa.model.Doa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: DoaDao) : ViewModel() {
    val allDoa: StateFlow<List<Doa>> = dao.getAllDoa().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}

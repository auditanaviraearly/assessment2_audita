package com.audita3077.ayodoa.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audita3077.ayodoa.database.DoaDao
import com.audita3077.ayodoa.model.Doa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: DoaDao) : ViewModel() {

    // MutableStateFlow untuk menyimpan Doa yang sedang ditampilkan
    private val _doa = MutableStateFlow<Doa?>(null)
    val doa: StateFlow<Doa?> = _doa

    // Mengambil doa berdasarkan ID dan memperbarui StateFlow
    fun loadDoaById(id: Int) {
        viewModelScope.launch {
            val doaData = dao.getById(id)
            _doa.value = doaData
        }
    }
    // Fungsi untuk menghapus doa
    fun deleteDoa(doa: Doa) {
        viewModelScope.launch {
            dao.delete(doa)
        }
    }

    // Fungsi untuk mengupdate doa
    fun updateDoa(doa: Doa) {
        viewModelScope.launch {
            dao.update(doa)
        }
    }
}
